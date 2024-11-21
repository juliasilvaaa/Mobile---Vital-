package br.senai.sp.jandira.vital.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.ui.theme.VitalTheme


@Composable
fun TelaAlterarSenha(controleDeNavegacao: NavHostController) {

    var novasenhaState = remember {
        mutableStateOf("")
    }

    var erroState = remember {
        mutableStateOf(false)
    }

    var mensagemErroState = remember {
        mutableStateOf("")
    }


    VitalTheme {

        Surface {
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF2954C7),
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 50.dp)
            ){

                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .align(Alignment.CenterStart) // Alinha à esquerda, centralizado verticalmente
                        .padding(start = 16.dp) // Adiciona um espaçamento à esquerda
                )
                Text(
                    "Alterar Senha",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center) // Centraliza no meio
                )



            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(top = 200.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {
                        novasenhaState.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                    ,
                    isError = erroState.value,
                    label = {
                        Text(
                            text = "Nova Senha",
                            fontSize = 13.sp
                        )
                    },
                    shape = RoundedCornerShape(26.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2954C7),
                        unfocusedBorderColor = Color(0xFFA09C9C)
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = {
                        novasenhaState.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                    ,
                    isError = erroState.value,
                    label = {
                        Text(
                            text = "Confirmar Nova Senha",
                            fontSize = 13.sp
                        )
                    },
                    shape = RoundedCornerShape(26.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2954C7),
                        unfocusedBorderColor = Color(0xFFA09C9C)
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = "", tint = Color(0xFF2954C7))
                    }

                )



//                Botao para Salvar a alteracao
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 60.dp),
                    verticalArrangement = Arrangement.Bottom // Posiciona os elementos no final da tela
                ) {

                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier
                            .height(48.dp)
                            .width(266.dp)
                            .align(Alignment.CenterHorizontally) // Centraliza o botão horizontalmente
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
                    ) {
                        Text(
                            text = "SALVAR",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }



            }

        }
    }


}



@Preview (showBackground = true, showSystemUi = true)
@Composable
fun TelaAlterarSenhaPreview () {


    // Pre-visualizacao
    VitalTheme {
        TelaAlterarSenha(controleDeNavegacao = NavHostController(LocalContext.current))
    }




}

