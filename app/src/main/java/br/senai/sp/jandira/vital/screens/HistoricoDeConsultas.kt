package br.senai.sp.jandira.vital.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


    VitalTheme {
        Surface {
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF2954C7),
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .fillMaxWidth()
                    .height(200.dp)
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

