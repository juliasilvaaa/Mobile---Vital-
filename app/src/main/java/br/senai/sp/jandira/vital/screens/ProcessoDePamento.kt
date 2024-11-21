package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.ui.theme.VitalTheme


@Composable
fun ProcessoDoPagamento() {



    VitalTheme {

        Box(
            modifier = Modifier
                .background(
                    Color(0xFF2954C7),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .fillMaxWidth()
                .height(100.dp)
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
                "Pagamento",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .align(Alignment.Center) // Centraliza no meio
            )



        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 160.dp), // Preenche a tela toda
            contentAlignment = Alignment.Center // Alinha o conteúdo da Box no centro da tela
        ) {

            Row(
                modifier = Modifier

//                    .padding(top = 150.dp, start = 30.dp)

            ) {

                Column(
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .background(color = Color(0xffD9D9D9), shape = RoundedCornerShape(360.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.transferencia),
                        contentDescription = "",
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 5.dp)

                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column {
                    Image(
                        painter = painterResource(R.drawable.line),
                        contentDescription = "",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .padding(top = 15.dp)
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .background(color = Color(0xff2954C7), shape = RoundedCornerShape(360.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.carregadobranco),
                        contentDescription = "",
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column {
                    Image(
                        painter = painterResource(R.drawable.line),
                        contentDescription = "",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .padding(top = 15.dp)
                    )
                }

                Spacer(modifier = Modifier.width(15.dp))

                Column(
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp)
                        .background(color = Color(0xffD9D9D9), shape = RoundedCornerShape(360.dp))
                ) {
                    Image(
                        painter = painterResource(R.drawable.transferenciaazul),
                        contentDescription = "",
                        modifier = Modifier
                            .width(45.dp)
                            .height(45.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 9.dp)
                    )
                }
            }
        }


        Column (
            modifier = Modifier
                .padding(top = 280.dp)
                .width(800.dp)
                .height(500.dp)
                .border(
                    width = 6.dp, // Largura da borda
                    color = Color(0x80E3E3E3), // Cor da borda
                    shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp) // A mesma forma da borda
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)

                )


        ){
            Column (
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 40.dp)
                    .width(247.dp)
                    .height(77.dp)
                    .background(color = Color(0xff2954C7), shape = RoundedCornerShape(20.dp))

            ){
                Text(
                    "Valor a ser pago",
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "R$ 120.00",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Column (
                modifier = Modifier
                    .padding(start = 17.dp, top = 17.dp)
            ){
                Text(
                    "ID da transferência",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    "Status",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    "valor da Transferêrencia",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    "Data",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    "Hora",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    "Forma de Pagamento",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )



            }

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .height(58.dp)
                    .width(326.dp)
                    .align(Alignment.CenterHorizontally)
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


    }


}



@Preview (showBackground = true, showSystemUi = true)
@Composable
fun ProcessoDoPagamentoPreview () {


    // Pre-visualizacao
    VitalTheme {
        ProcessoDoPagamento()
    }




}
