package br.senai.sp.jandira.vital.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Sexo
import br.senai.sp.jandira.vital.model.SexoResponse
import br.senai.sp.jandira.vital.model.Usuario
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.service.SexoService
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ServiceCast")
@Composable
fun TelaCadastro(controleDeNavegacao: NavHostController) {
    val context = LocalContext.current
    val usuarioService = RetrofitFactory().getUserService()
    val sexoService = RetrofitFactory().getSexoService(SexoService::class.java)

    var nomeState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var cpfState by remember { mutableStateOf("") }
    var sexoSelecionadoState by remember { mutableStateOf(0) }
    var senhaState by remember { mutableStateOf("") }
    var confirmarSenhaState by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var listaSexos by remember { mutableStateOf(listOf<Sexo>()) }

    // Carregar os dados de sexo ao abrir a tela
    LaunchedEffect(Unit) {
        sexoService.listarSexos().enqueue(object : Callback<SexoResponse> {
            override fun onResponse(call: Call<SexoResponse>, response: Response<SexoResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        listaSexos = responseBody.sexos
                        Log.d("TelaCadastro", "Sexos recebidos: $listaSexos")
                    } else {
                        Toast.makeText(context, "Erro: Resposta vazia", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Erro ao carregar sexos", Toast.LENGTH_SHORT).show()
                    Log.e("TelaCadastro", "Erro na resposta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<SexoResponse>, t: Throwable) {
                Toast.makeText(context, "Falha ao buscar dados de sexo", Toast.LENGTH_SHORT).show()
                Log.e("TelaCadastro", "Falha na API: ${t.message}")
            }
        })
    }
    // Função para salvar o usuário
    fun salvarUsuario() {
        if (senhaState == confirmarSenhaState) {
            val usuario = Usuario(
                nome = nomeState,
                email = emailState,
                cpf = cpfState,
                id_sexo = sexoSelecionadoState,
                senha = senhaState,
                data_nascimento = selectedDate,
                foto = "",
                isOver = false
            )

            usuarioService.salvarUsuario(usuario).enqueue(object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        controleDeNavegacao.navigate("telaLogin")
                    } else {
                        Toast.makeText(context, "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(context, "Falha na conexão com o servidor.", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(context, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
        }
    }

    // Use LocalSoftwareKeyboardController to hide the keyboard
    val keyboardController = LocalSoftwareKeyboardController.current
    VitalTheme {
        Column {
            Surface(
                modifier = Modifier
                    .height(250.dp)
                    .offset(y = -15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onda),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .width(140.dp)
                            .height(110.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Criar Conta", fontSize = 28.sp, color = Color(0xFF2954C7))

                OutlinedTextField(
                    value = nomeState,
                    onValueChange = { nomeState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nome") },
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                OutlinedTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email") },
                    leadingIcon = {
                        Icon(Icons.Filled.Email, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                OutlinedTextField(
                    value = cpfState,
                    onValueChange = { cpfState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "CPF") },
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Data de Nascimento") },
                    visualTransformation = DateVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = {
                        Icon(Icons.Filled.DateRange, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )
                OutlinedTextField(
                    value = listaSexos.find { it.id_sexo == sexoSelecionadoState }?.descricao ?: "Selecione o sexo",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Esconde o teclado
                            keyboardController?.hide()

                            // Abre o DropdownMenu
                            expanded = true
                        },
                    label = { Text(text = "Sexo") },
                    leadingIcon = {
                        Icon(Icons.Filled.Face, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                // Ajuste do DropdownMenu
                if (expanded) {
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        listaSexos.forEach { sexo ->
                            DropdownMenuItem(
                                text = { Text(sexo.descricao) },
                                onClick = {
                                    sexoSelecionadoState = sexo.id_sexo
                                    expanded = false
                                }
                            )
                        }
                    }
                }



                OutlinedTextField(
                    value = senhaState,
                    onValueChange = { senhaState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Senha") },
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                OutlinedTextField(
                    value = confirmarSenhaState,
                    onValueChange = { confirmarSenhaState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Confirmar Senha") },
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                Button(
                    onClick = { salvarUsuario() },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Text(text = "Cadastrar", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    }
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = formatoData(text.text)
        return TransformedText(AnnotatedString(formattedText), OffsetMapping.Identity)
    }
}

fun formatoData(text: String): String {
    val formatted = StringBuilder()
    for (i in text.indices) {
        formatted.append(text[i])
        if (i == 1 || i == 3) formatted.append("/")
    }
    return formatted.toString()

    }