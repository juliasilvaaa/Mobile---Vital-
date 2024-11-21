package br.senai.sp.jandira.vital.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.vital.model.Medicos
import br.senai.sp.jandira.vital.model.ResultMedicos
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TelaMedicos(controleDeNavegacao: NavHostController) {

    // Lista de Médicos
    var medicosList by remember {
        mutableStateOf<List<Medicos>>(emptyList())
    }

    // Efetuar a Requisição da API
    LaunchedEffect(key1 = Unit) {
        val callMedicosList = RetrofitFactory()
            .getMedicoService()
            .getAllMedicos()

        callMedicosList.enqueue(object : Callback<ResultMedicos> {
            override fun onResponse(call: Call<ResultMedicos>, response: Response<ResultMedicos>) {
                if (response.isSuccessful) {
                    val medicoResponse = response.body()?.medico
                    Log.d("API_RESPONSE", "Resposta: ${response.body()}") // Mostra o JSON completo
                    if (medicoResponse != null && medicoResponse.isNotEmpty()) {
                        medicosList = medicoResponse
                        Log.d("API_RESPONSE", "Médicos recebidos: ${medicoResponse.size}")
                    } else {
                        Log.d("API_RESPONSE", "Nenhum médico encontrado")
                    }
                } else {
                    Log.d("API_RESPONSE", "Erro: ${response.code()} - ${response.errorBody()?.string()}")
                }
            }


            override fun onFailure(call: Call<ResultMedicos>, t: Throwable) {
                // Lidar com a falha na requisição
            }
        })
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
                    "Médicos",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                modifier = Modifier.padding(top = 180.dp)
            ) {
                // Campo de pesquisa
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    modifier = Modifier
                        .width(360.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color(0xFFF6F6F6)),
                    placeholder = {
                        Text(
                            text = "Pesquise por médico",
                            color = Color(0xFFA09C9C)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "",
                                tint = Color(0xFFA09C9C)
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    )
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(medicosList) { medico ->
                        MedicosCard(medico, controleDeNavegacao)
                    }
                }
            }
        }
    }
}

@Composable
fun MedicosCard(medicos: Medicos, controleDeNavegacao: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    controleDeNavegacao.navigate("infoMedico/${medicos.id_medico}")
                }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = medicos.foto_medico,
                    contentDescription = "Foto do Médico",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Dr. ${medicos.nome_medico}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF579EC5)
                )
                Text(
                    text = medicos.especialidade,
                    fontSize = 14.sp,
                    color = Color(0XFFA09C9C)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaMedicosPreview() {
    VitalTheme {
        TelaMedicos(rememberNavController())
    }
}
