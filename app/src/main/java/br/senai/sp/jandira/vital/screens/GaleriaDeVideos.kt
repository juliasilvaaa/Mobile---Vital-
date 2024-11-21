package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.ui.theme.VitalTheme


@Composable
fun GaleriaDeVideos() {

    VitalTheme {

    }


}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun VideoChamadaPreview () {

//    val categorias = CategoriaRepository

    // Pre-visualizacao
    VitalTheme {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xffE3E3E3)),
            verticalArrangement = Arrangement.Top
        ){

            Image(
                painter = painterResource(R.drawable.ondas),
                contentDescription = "",
                modifier = Modifier
                    .width(500.dp)
                    .height(300.dp)
                    .offset(y = -40.dp)

            )

            Text(
                text = "Galeria",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 40.dp)
                    .offset(y = -200.dp)
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .height(30.dp)
                    .width(330.dp)
                    .offset(y = -180.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.lupa),
                    contentDescription = "",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .offset(x = 130.dp)
                )

            }



            Text(
                "Saúde Mental",
                fontSize = 10.sp,
                color = Color(0xff0073DE),
                modifier = Modifier
                    .offset(y = -35.dp, x = 5.dp)
            )


            LazyRow {
                items(3) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .height(112.dp)
                            .offset(y = -40.dp)
                            .padding(10.dp)
                            .background(color = Color.Red, shape = RoundedCornerShape(5.dp))


                    ) {
                        Image(
                            painter = painterResource(R.drawable.player),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(top = 32.dp)
                                .width(30.dp)
                                .height(30.dp)

                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Idoso",
                fontSize = 10.sp,
                color = Color(0xff0073DE),
                modifier = Modifier
                    .offset(y = -35.dp, x = 5.dp)
            )

            LazyRow {
                items(3) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .height(112.dp)
                            .offset(y = -40.dp)
                            .padding(10.dp)
                            .background(color = Color.Red, shape = RoundedCornerShape(5.dp))


                    ) {
                        Image(
                            painter = painterResource(R.drawable.player),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(top = 32.dp)
                                .width(30.dp)
                                .height(30.dp)

                        )
                    }
                }
            }

            Text(
                "Saúde Sexual",
                fontSize = 10.sp,
                color = Color(0xff0073DE),
                modifier = Modifier
                    .offset(y = -35.dp, x = 5.dp)
            )

            LazyRow {
                items(3) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .height(112.dp)
                            .offset(y = -40.dp)
                            .padding(10.dp)
                            .background(color = Color.Red, shape = RoundedCornerShape(5.dp))


                    ) {
                        Image(
                            painter = painterResource(R.drawable.player),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(top = 32.dp)
                                .width(30.dp)
                                .height(30.dp)

                        )
                    }
                }
            }

            Text(
                "Pais DePrimeira Viagem",
                fontSize = 10.sp,
                color = Color(0xff0073DE),
                modifier = Modifier
                    .offset(y = -35.dp, x = 5.dp)
            )

            LazyRow {
                items(3) {
                    Column(
                        modifier = Modifier
                            .width(200.dp)
                            .height(300.dp)
                            .offset(y = -40.dp)
                            .padding(10.dp)
                            .background(color = Color.Red, shape = RoundedCornerShape(5.dp))


                    ) {
                        Image(
                            painter = painterResource(R.drawable.player),
                            contentDescription = "",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(top = 32.dp)
                                .width(30.dp)
                                .height(30.dp)

                        )
                    }
                }
            }

            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .height(80.dp)
                    .width(326.dp)
                    .align(Alignment.CenterHorizontally)
                    .offset(y = -80.dp)
                    .background(
                        color = Color.White, // Cor de fundo
                        shape = RoundedCornerShape(20.dp) // Cantos arredondados
                    )
                    .border(
                        width = 2.dp, // Largura da borda
                        color = Color(0xffE3E3E3), // Cor da borda
                        shape = RoundedCornerShape(20.dp) // A mesma forma da borda
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent // Transparente para ver o fundo aplicado com .background
                ),
                shape = RoundedCornerShape(20.dp)
            ) {

                Row {

                    Column {
                        Image(
                            painter = painterResource(R.drawable.casa),
                            contentDescription = "",
                            modifier = Modifier
                                .width(26.dp)
                                .height(26.dp)

                        )

                        Text(
                            "ínico",
                            color = Color.Gray,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .offset(y = -4.dp)

                        )
                    }

                    Spacer(modifier = Modifier.width(34.dp))

                    Column {
                        Image(
                            painter = painterResource(R.drawable.fav),
                            contentDescription = "",
                            modifier = Modifier
                                .width(22.dp)
                                .height(22.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Text(
                            "Favoritos",
                            color = Color.Gray,
                            fontSize = 10.sp,

                            )
                    }

                    Spacer(modifier = Modifier.width(34.dp))

                    Column {
                        Image(
                            painter = painterResource(R.drawable.carrinhodecompras),
                            contentDescription = "",
                            modifier = Modifier
                                .width(22.dp)
                                .height(22.dp)
                                .align(Alignment.CenterHorizontally)

                        )

                        Text(
                            "Carrinho",
                            color = Color.Gray,
                            fontSize = 10.sp,
                            modifier = Modifier

                        )
                    }

                    Spacer(modifier = Modifier.width(34.dp))

                    Column {
                        Image(
                            painter = painterResource(R.drawable.notificacao),
                            contentDescription = "",
                            modifier = Modifier
                                .width(22.dp)
                                .height(22.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        Text(
                            "Notificações",
                            color = Color.Gray,
                            fontSize = 10.sp

                        )
                    }
                }
            }



        }
        GaleriaDeVideos()
    }


}