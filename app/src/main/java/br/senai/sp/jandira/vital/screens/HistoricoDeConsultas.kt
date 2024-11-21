package br.senai.sp.jandira.vital.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Usuario
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




@Composable
fun HistoricoDeConsultas() {


    VitalTheme {
        Surface {
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF2954C7),
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .fillMaxWidth()
                    .height(130.dp)
                    .offset(y = -16.dp)
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
                    "Consultas",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center) // Centraliza no meio
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
        HistoricoDeConsultas()
    }




}

