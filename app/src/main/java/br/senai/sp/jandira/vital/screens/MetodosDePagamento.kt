package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Surface
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
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.ui.theme.VitalTheme


@Composable
fun MetodosDePagamento(controleDeNavegacao: NavHostController, horarioSelecionado: String?) {


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
                            controleDeNavegacao.navigate("telaInicio")
                        }
                )
                Text(
                    "Pagamento",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 180.dp)
                    .padding(10.dp)
            ) {

                Text(
                    "Selecione a forma de pagamento",
                    fontSize = 18.sp
                )


                Column(
                    modifier = Modifier
                        .width(350.dp)
                        .height(45.dp)
                        .padding(top = 10.dp, start = 15.dp)
                ) {
                    Row {

                        Image(
                            painter = painterResource(R.drawable.dinheiro),
                            contentDescription = "",
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)
                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            "Pix",
                            fontSize = 11.sp
                        )
                    }

                }
                Spacer(modifier = Modifier.height(14.dp))

                Column(
                    modifier = Modifier
                        .width(350.dp)
                        .height(45.dp)
                        .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                        .padding(top = 10.dp, start = 15.dp)

                ) {
                    Row {

                        Image(
                            painter = painterResource(R.drawable.cartao),
                            contentDescription = "",
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp)

                        )

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            "Cartão Crédito/Débito",
                            fontSize = 11.sp,
                            modifier = Modifier
                                .padding()
                        )

                        Text(
                            text = "Horário Selecionado: $horarioSelecionado",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                }

                Spacer(modifier = Modifier.height(400.dp))

                Row(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                ) {

                    Column {

                        Text(
                            "Total",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "R$ 120.00",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Text(
                        "ver detalhes",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 23.dp, start = 10.dp)
                    )

                    Spacer(modifier = Modifier.width(120.dp))

                    Button(
                        onClick = { controleDeNavegacao.navigate("telaProcesso") },
                        modifier = Modifier
                            .height(42.dp)
                            .width(135.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2954C7)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        // Texto dentro do botao
                        Text(
                            text = "Pagar",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White

                        )

                    }
                }
//                Spacer(modifier = Modifier.weight(1f)) // Um espaço vazio que empurra o botão para baixo
//                verticalArrangement = Arrangement.Bottom // Posiciona os elementos no final da tela

//                Botao para Salvar a alteracao


            }


        }


    }

}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun Preview () {


    // Pre-visualizacao
    VitalTheme {

    }




}