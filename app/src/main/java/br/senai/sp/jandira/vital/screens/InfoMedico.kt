package br.senai.sp.jandira.vital.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.model.Medicos
import br.senai.sp.jandira.vital.model.ResultMedico
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@Composable
fun InfoMedico(controleDeNavegacao: NavHostController, idMedico: String?) {
    val idMedicoInt = idMedico?.toIntOrNull() ?: 0  // Garantir que idMedico seja um Int válido

    // Variáveis de estado para armazenar o médico e o estado de carregamento
    var medic by remember { mutableStateOf<Medicos?>(null) }
    var isLoading by remember { mutableStateOf(true) }  // Estado de carregamento

    // Requisição da API para buscar o médico
    LaunchedEffect(key1 = idMedicoInt) {
        val callMedico = RetrofitFactory()
            .getMedicoService()
            .getMedicoById(idMedicoInt)

        callMedico.enqueue(object : Callback<ResultMedico> {
            override fun onResponse(call: Call<ResultMedico>, response: Response<ResultMedico>) {
                if (response.isSuccessful) {
                    val medicoResponse = response.body()?.medico

                    // Verifique se há médicos na resposta e pegue o primeiro da lista
                    if (!medicoResponse.isNullOrEmpty()) {
                        medic = medicoResponse.first()  // Pega o primeiro médico da lista
                    }
                }
                isLoading = false  // Atualiza o estado para indicar que o carregamento terminou
            }

            override fun onFailure(call: Call<ResultMedico>, t: Throwable) {
                isLoading = false  // Atualiza o estado para indicar que o carregamento terminou
            }
        })
    }

    // Renderizar os dados do médico na UI
    VitalTheme {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Cabeçalho com o Box
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFC6E1FF),
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    // Ícone de voltar
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color(0xFF565454),
                        modifier = Modifier
                            .align(Alignment.TopStart) // Alinha ao topo e início do Box
                            .padding(start = 16.dp, top = 16.dp) // Espaço em relação às bordas
                            .clickable {
                                controleDeNavegacao.navigate("telaMedicos")
                            }
                    )

                    // Coluna centralizada no Box
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp), // Espaçamento em relação ao topo
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Imagem do médico
                        AsyncImage(
                            model = medic?.foto_medico,
                            contentDescription = "Foto do Médico",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )

                        if (isLoading) {
                            Text(
                                text = "Carregando...",
                                fontSize = 18.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        } else {
                            Text(
                                text = "Dr. ${medic?.nome_medico ?: "Nome não encontrado"}",
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 16.dp)
                            )

                            // Especialidade
                            medic?.let {
                                Text(
                                    text = it.especialidade,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }

                // Seção "Sobre" e descrição
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp) // Espaçamento em relação às bordas do conteúdo
                ) {
                    Text(
                        text = "Sobre",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = medic?.descricao ?: "Descrição não encontrada",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Column (
                        modifier = Modifier
                            .padding(16.dp)
                            .padding(top = 110.dp)
                    ){

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .height(46.dp)
                                .width(300.dp)
                                .height(50.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF77B8FF), Color(0xFF0133D6))
                                    ),
                                    shape = RoundedCornerShape(30.dp) // Define o formato do botão
                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent // Para garantir que o gradiente seja visível
                            ),
                            contentPadding = PaddingValues()
                        ) {
                            Text("Selecione o dia e hora")
                        }
                    }

                }
            }
        }
    }
}
