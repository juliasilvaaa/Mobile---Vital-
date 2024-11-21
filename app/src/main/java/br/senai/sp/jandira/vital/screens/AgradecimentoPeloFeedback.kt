package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import okhttp3.internal.wait


@Composable
fun AgradecimentoPeloFeedback() {



    VitalTheme {



        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xffC6E1FF)),
            verticalArrangement = Arrangement.Bottom
        ) {

            Image(
                painter = painterResource(R.drawable.confere),
                contentDescription = "",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .align(Alignment.CenterHorizontally)
//                    .padding(bottom = 90.dp)

            )

            Spacer(modifier = Modifier.height(38.dp))

            Text(
                "Obrigado por avaliar!",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )

            Text(
                "Sua avaliação, nos ajuda a melhorar",
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(490.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    )
                    .align(Alignment.CenterHorizontally)


            ) {

                Column (
                    modifier = Modifier
                        .width(311.dp)
                        .height(376.dp)
                        .offset(y = -50.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(color = Color.White, shape = RoundedCornerShape(18.dp))
                        .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                ){

                    Row (
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 20.dp)

                    ){

                        Box(
                            modifier = Modifier
                                .width(70.dp)
                                .height(70.dp)
                                .background(color = Color(0xff0174DE), shape = RoundedCornerShape(10.dp))

                        ) {
                            Image(
                                painter = painterResource(R.drawable.medica),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(70.dp)
                                    .padding(7.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Column {
                            Text(
                                "Dr. Lara Silva Costa",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                "Dermatologista",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Image(
                            painter = painterResource(R.drawable.editar),
                            contentDescription = "",
                            modifier = Modifier
                                .height(15.dp)
                                .width(15.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Image(
                        painter = painterResource(R.drawable.linha),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(257.dp)
                            .height(4.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        "Uma excelente médica, trabalha com dedicação, cuidado e profissionalismo. Todas minhas consultas são com ela, nunca mais marco com outra pessoa, ela mudou a minha vida e a minha saúde",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 23.dp, end = 43.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row (
                        modifier = Modifier.fillMaxSize(), // Faz a Row preencher toda a largura
                        horizontalArrangement = Arrangement.Start, // Alinha os itens no final da Row (direita)
                        verticalAlignment = Alignment.Bottom
                    ){
                        Text(
                            "15 Out 2023",
                            fontSize = 8.sp,
                            modifier = Modifier
                                .padding(10.dp)
                        )

                        Spacer(modifier = Modifier.width(170.dp))

                        Image(
                            painter = painterResource(R.drawable.estrela),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(bottom = 13.dp)
                        )

                        Image(
                            painter = painterResource(R.drawable.estrela),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(bottom = 13.dp)
                        )

                        Image(
                            painter = painterResource(R.drawable.estrela),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(bottom = 13.dp)
                        )

                        Image(
                            painter = painterResource(R.drawable.estrela),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(bottom = 13.dp)
                        )

                        Image(
                            painter = painterResource(R.drawable.estrela),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(bottom = 13.dp)
                        )
                    }
                }

                Button(
                    onClick = { /* Ação ao clicar no botão */ },
                    modifier = Modifier
                        .padding(16.dp) // Espaçamento ao redor do botão
                        .height(43.dp) // Altura do botão
                        .width(318.dp) // Largura do botão
                        .align(Alignment.CenterHorizontally)
                        .shadow(2.dp, shape = RoundedCornerShape(12.dp)), // Sombra com elevação de 8dp e cantos arredondados
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0174DE)), // Cor de fundo do botão
                    shape = RoundedCornerShape(12.dp) // Cantos arredondados no botão
                ) {
                    Text(
                        text = "Feito",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }


}



@Preview (showBackground = true, showSystemUi = true)
@Composable
fun AgradecimentoPeloFeedbackPreview () {


    // Pre-visualizacao
    VitalTheme {
        AgradecimentoPeloFeedback()
    }




}