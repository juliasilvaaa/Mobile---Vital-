package br.senai.sp.jandira.vital.screens

import android.widget.Space
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.shadow
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
fun MedicosFavoritos() {


    VitalTheme {

        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xffEAEAEA))
        ){

            Image(
                painter = painterResource(R.drawable.ondas),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(236.dp)
            )

            LazyColumn {
                items(6){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                        ){
                            // Conteúdo da coluna
                            Column(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(80.dp)
                                    .padding(10.dp)
                                    .background(color = Color.Gray, shape = RoundedCornerShape(360.dp))
                            ) {

                            }

                            Column {

                                Text(
                                    "Dr Lara Silva Costa",
                                    fontSize = 14.sp,
                                    color = Color(0xff579EC5),
                                    modifier = Modifier
                                        .padding(top = 14.dp)
                                        .height(19.dp)
                                )

                                Text(
                                    "Meu nome é Lara, é um prazer te atender",
                                    fontSize = 10.sp,
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .height(19.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(50.dp))

                            Image(
                                painter = painterResource(R.drawable.estrelaazul),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(35.dp)
                                    .height(35.dp)
                                    .padding(top = 14.dp)
                            )

                            Text(
                                "4.7",
                                fontSize = 10.sp,
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(top = 12.dp)
                            )

                        }

                        // Linha de borda inferior cinza
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp) // Define a espessura da borda
                                .background(Color.Gray) // Define a cor da borda como cinza
                        )
                    }
                }
            }

            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .height(58.dp)
                    .width(326.dp)
                    .align(Alignment.CenterHorizontally)
                    .shadow(2.dp, shape = RoundedCornerShape(20.dp))
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
fun MedicosFavoritosPreview () {


    // Pre-visualizacao
    VitalTheme {
        MedicosFavoritos()
    }




}