package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.ui.theme.VitalTheme


@Composable
fun HistoricoDeConsultas(controleDeNavegacao: NavHostController, idUsuario: Int) {

    // Variavel do card
    val isCardVisible = remember { mutableStateOf(false) }

    VitalTheme {
        Surface {
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF2954C7),
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 20.dp)
            ) {
                // Icon clicável para voltar
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .clickable {
                            controleDeNavegacao.navigate("telaInicio/$idUsuario")
                        }
                )
                Text(
                    "Consultas",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center)
                )

                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .align(Alignment.Center)
                        .offset(y = 40.dp)
                        .width(90.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFC6E1FF), shape = RoundedCornerShape(20.dp))
                        .zIndex(1f)
                ) {
                    Text(
                        text = "Histórico",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF565454),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                }

            }

            Column (
                modifier = Modifier
                    .padding(top = 200.dp)
                    .padding(16.dp)
            ) {

                Text(
                    text = "Próximas",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                Card (
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        )  {
                            Text(
                                text = "Especialidade: Ginecologia",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Info",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .clickable {
                                        isCardVisible.value = !isCardVisible.value
                                    }
                            )

                        }
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Médico : Pietra Volpato",
                            color = Color(0xFF0073DE)
                        )
                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Data: 28/08/24",
                            fontWeight = FontWeight.Bold
                        )


                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFAF2AB),
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(text = "Em andamento")
                            }

                            Text(text = "Horário: 12:00")

                        }
                    }

                }
                if (isCardVisible.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .absoluteOffset(y = (-50).dp)
                            .zIndex(2f)
                            .padding(16.dp)
                            .background(Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    ){
                        Column {
                            Text(
                                text = "Detalhes adicionais",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Informações extras sobre a consulta.",
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(onClick = { isCardVisible.value = false }) {
                                    Text("Fechar")
                                }
                                Button(onClick = {
                                    controleDeNavegacao.navigate("telaChamada")
                                }) {
                                    Text("Iniciar")
                                }
                            }
                        }
                    }
                }



                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Anteriores",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))


                // Card Consulta Concluída
                Card (
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp) // Espaco entre as bordas
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Especialidade: Ginecologia",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Info",
                                tint = Color.Black
                            )

                        }
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Médico : Pietra Volpato",
                            color = Color(0xFF0073DE)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Data: 28/08/24",
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Green,
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(text = "Concluída")
                            }

                            Text(
                                text = "Horário: 12:00",
                                color = Color.Black
                            )

                        }
                    }

                }

                Spacer(modifier = Modifier.height(18.dp))

                // Card Consulta Cancelada

                Card (
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Column (
                        modifier = Modifier
                            .padding(12.dp) // Espaco entre as bordas
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Especialidade: Ginecologia",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Info",
                                tint = Color.Black
                            )

                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Médico : Pietra Volpato",
                            color = Color(0xFF0073DE)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "Data: 28/08/24",
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFC4D4D),
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(text = "Cancelada")
                            }

                            Text(
                                text = "Horário: 12:00",
                                color = Color.Black
                            )

                        }
                    }

                }
            }


        }
    }

}


@Preview (showBackground = true, showSystemUi = true)
@Composable
fun HistoricoDeConsultasPreview () {

    // Pre-visualizacao
    VitalTheme {

    }

}
