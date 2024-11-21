package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.ui.theme.VitalTheme

@Composable
fun TelaInicial1(controleDeNavegacao: NavHostController) {
    Scaffold(
        content = { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Imagem
                    Image(
                        painter = painterResource(id = R.drawable.medica),
                        contentDescription = null,
                        modifier = Modifier
                            .width(600.dp)
                            .height(600.dp)
                            .offset(y = 70.dp)
                    )

                    // Gradiente
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x3FFFFFF),
                                        Color(0x03FFFFFF),
                                        Color(0xFF0073DE),
                                        Color(0xFF0073DE)
                                    )
                                )
                            )
                    )

                    // Texto "Pular"
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd) // Alinha no topo à direita

                    ) {
                        Button(
                            onClick = {
                                controleDeNavegacao.navigate("telaLogin")
                            },
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent  // Deixa o botão transparente
                            ),
                            elevation = ButtonDefaults.buttonElevation(0.dp)  // Remove a elevação (sombra)
                        ) {
                            Text(
                                text = "Pular",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }

                    }

                    // Texto FINAIS
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // Alinha ao centro na parte inferior
                            .padding(26.dp)
                            .padding(bottom = 16.dp), // Espaçamento inferior
                        horizontalAlignment = Alignment.CenterHorizontally // Alinha os textos ao centro
                    ) {
                        Text(
                            text = "Telemedicina",
                            fontSize = 16.sp,
                            color = Color.White
                        )

                        Text(
                            text = "Agende uma consulta de telemedicina com um médico em poucos minutos",
                            fontSize = 12.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()  // Ocupa a largura total da tela
                        ) {
                            Button(
                                onClick = {
                                    controleDeNavegacao.navigate("telaInicial2")
                                },
                                modifier = Modifier
                                    .align(Alignment.CenterEnd),  // Alinha o botão à direita
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent  // Deixa o botão transparente
                                ),
                                elevation = ButtonDefaults.buttonElevation(0.dp)  // Remove a elevação (sombra)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = "",
                                    tint = Color.White  // Cor branca para o ícone
                                )
                            }
                        }




                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaInicial1Preview() {
    VitalTheme {
        TelaInicial1(controleDeNavegacao = NavHostController(LocalContext.current))
    }
}