package br.senai.sp.jandira.vital.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Login
import br.senai.sp.jandira.vital.model.UsuarioLogin
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaLogin(controleDeNavegacao: NavHostController) {

    // Criando variaves de estado
    var emailState = remember {
        mutableStateOf("")
    }

    var senhaState = remember {
        mutableStateOf("")
    }

    var erroLoginState = remember {
        mutableStateOf(false)
    }

    var mensagemErroState = remember {
        mutableStateOf("")
    }

    var isLoading = remember { mutableStateOf(false) }



    VitalTheme {
        Column  {
            Surface(
                modifier = Modifier
                    .height(250.dp)
                    .offset(y = -15.dp)

            ){
                // Imagem da onda
                Image(
                    painter = painterResource(id = R.drawable.onda),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()

                )

                Column  (
                    modifier = Modifier
                        .fillMaxSize()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    // Imagem da logo
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .width(140.dp)
                            .height(110.dp)
                            .padding(top = 0.dp)
                    )
                }




            }

            Column (
                modifier = Modifier
                    .padding(top = 0.dp, start = 26.dp, end = 26.dp, bottom = 26.dp), // Reduzindo o padding superior
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(80.dp))


                Text(
                    text = "Login",
                    fontSize = 28.sp,
                    color = Color(0xFF2954C7)
                )

                // Espaco para inserir o email
                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = {
                        emailState.value = it
                    },
                    isError = erroLoginState.value,
                    modifier = Modifier
                        .padding(top = 70.dp)
                        .fillMaxWidth(),
                    label = {
                        Text(text = "Email")
                    },
                    shape = RoundedCornerShape(26.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Email, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(
                    value = senhaState.value,
                    onValueChange = {
                        senhaState.value = it
                    },
                    isError = erroLoginState.value,
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .fillMaxWidth(),
                    label = {
                        Text(text = "Senha")
                    },
                    shape = RoundedCornerShape(26.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )


                Column (
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(top = 110.dp)
                ) {
                    Button(
                        onClick = {
                            isLoading.value = true

                            val login = Login(email = emailState.value, senha = senhaState.value)
                            Log.i("USUARIO", login.toString())

                            RetrofitFactory().getUserService().loginUsuario(login).enqueue(object : Callback<UsuarioLogin> {
                                override fun onResponse(call: Call<UsuarioLogin>, response: Response<UsuarioLogin>) {
                                    isLoading.value = false

                                    if (response.isSuccessful) {
                                        val usuario = response.body()
                                        if (usuario != null) {
                                            // Sucesso! O login foi realizado com sucesso.
                                            Log.d("NAVEGACAO", "Nome do usuário: ${usuario.nome}")
                                            // Navega para a TelaHome, passando o id do usuário ou token se necessário
                                            val idUsuario = usuario.id_usuario ?: 0
                                            controleDeNavegacao.navigate("telaHome/$idUsuario")
                                        } else {
                                            erroLoginState.value = true
                                            mensagemErroState.value = "Erro: credenciais inválidas."
                                            Log.e("TelaLogin", "Corpo da resposta é nulo")
                                        }
                                    } else {
                                        erroLoginState.value = true
                                        mensagemErroState.value = "Erro: credenciais inválidas."
                                        Log.e("TelaLogin", "Resposta não bem sucedida: ${response.code()}")
                                    }
                                }


                                override fun onFailure(call: Call<UsuarioLogin>, t: Throwable) {
                                    Log.i("Falhou:", t.toString())
                                    isLoading.value = false
                                    erroLoginState.value = true
                                    mensagemErroState.value = "Erro de conexão: ${t.message}"
                                }
                            })


                        },
                        modifier = Modifier
                            .height(46.dp)
                            .width(300.dp)
                            .height(50.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF77B8FF), Color(0xFF0133D6))
                            ),
                    shape = RoundedCornerShape(30.dp) // Define o formato do botão
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent // Para garantir que o gradiente seja visível
                    ),
                    contentPadding = PaddingValues() // Remove o padding padrão para o gradiente preencher todo o botão
                    )
                         {
                        // Texto dentro do botao
                        Text(
                            text = "ENTRAR",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
                Text(
                    text = "Nao possui uma conta?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light

                )
                Text(
                    text = "Cadastre-se Aqui",
                    fontSize = 16.sp,
                    color = Color(0xFF0436D7),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clickable {
                            if (controleDeNavegacao != null) {
                                controleDeNavegacao.navigate("telaCadastro")
                            }
                        }

                )

            }


        }

    }


}


@Preview (showBackground = true, showSystemUi = true)
@Composable
fun TelaLoginPreview () {

    // Pre-visualizacao
    VitalTheme {
//        TelaLogin(controleDeNavegacao)
    }


}