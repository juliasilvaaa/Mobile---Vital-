package br.senai.sp.jandira.vital.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.model.*
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun StarRating(rating: Int) {
    Row {
        for (i in 1..5) {
            when {
                i <= rating.toInt() -> {
                    // Estrela cheia
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Estrela cheia",
                        tint = Color(0xFF0174DE),
                        modifier = Modifier.size(20.dp)
                    )
                }
                i - rating <= 0.5 -> {
                    // Meia estrela (você pode adicionar um ícone customizado aqui)
                    Icon(
                        imageVector = Icons.Filled.Star, // Substitua por um ícone de meia estrela, se disponível
                        contentDescription = "Meia estrela",
                        tint = Color(0xFF0174DE),
                        modifier = Modifier.size(20.dp)
                    )
                }
                else -> {
                    // Estrela vazia
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Estrela vazia",
                        tint = Color.LightGray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoMedico(controleDeNavegacao: NavHostController, idMedico: String?) {
    val idMedicoInt = idMedico?.toIntOrNull() ?: 0

    var medic by remember { mutableStateOf<Medicos?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var avaliacoes by remember { mutableStateOf<List<Avaliacao>>(emptyList()) }

    LaunchedEffect(key1 = idMedicoInt) {
        Log.d("Avaliacoes", "ID do Médico: $idMedicoInt")

        // Carregar informações do médico
        val callMedico = RetrofitFactory()
            .getMedicoService()
            .getMedicoById(idMedicoInt)

        callMedico.enqueue(object : Callback<ResultMedico> {
            override fun onResponse(call: Call<ResultMedico>, response: Response<ResultMedico>) {
                if (response.isSuccessful) {
                    val medicoResponse = response.body()?.medico
                    if (!medicoResponse.isNullOrEmpty()) {
                        medic = medicoResponse.first()
                    }
                }
                isLoading = false
            }

            override fun onFailure(call: Call<ResultMedico>, t: Throwable) {
                isLoading = false
            }
        })

        val callAvaliacoes = RetrofitFactory()
            .getMedicoService()
            .getAvaliacoesMedico(idMedicoInt)

        callAvaliacoes.enqueue(object : Callback<AvaliacaoResponse> {
            override fun onResponse(call: Call<AvaliacaoResponse>, response: Response<AvaliacaoResponse>) {
                if (response.isSuccessful) {
                    avaliacoes = response.body()?.avaliacoes ?: emptyList()
                } else {
                    Log.e("ErroAvaliacoes", "Resposta não foi bem-sucedida: ${response.code()}")
                    // Log do corpo da resposta para investigar mais detalhes do erro
                    Log.e("ErroAvaliacoes", "Corpo da resposta: ${response.errorBody()?.string()}")
                }
            }


            override fun onFailure(call: Call<AvaliacaoResponse>, t: Throwable) {
                Log.e("ErroAvaliacoes", "Erro ao carregar avaliações: ${t.message}")
                t.printStackTrace()
            }

        })
    }

    VitalTheme {
        Surface {
            Column{
                // Cabeçalho com informações do médico
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFC6E1FF),
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .fillMaxWidth()
                        .height(340.dp)
                        .padding(top = 30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color(0xFF565454),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(16.dp)
                            .clickable { controleDeNavegacao.navigate("telaMedicos/$idMedico") }
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = medic?.foto_medico,
                            contentDescription = "Foto do Médico",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        medic?.let {
                            Text(
                                text = "Dr. ${it.nome_medico}"
                            )

                            Text(
                                text = it.especialidade
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            // Calcular a média
                            val mediaNotas = avaliacoes.map { it.avaliacao_nota }.average()
                            val mediaNotasArredondada = "%.1f".format(mediaNotas).toDouble()


                            // Exibir o texto da média e quantidade de avaliações
                            Text(
                                text = " ($mediaNotasArredondada)",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Text(
                                text = "${avaliacoes.size} Avaliações",
                                fontSize = 12.sp,
                                color = Color(0xFFA09C9C),
                                modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                            )
                        }

                    }
                }
            }

            // Avaliacoes do Medico
            Column (
                modifier = Modifier
                    .padding(top = 340.dp)
                    .padding(10.dp)
            ){

                Text(
                    text = "Sobre",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                medic?.let {
                    Text(
                        text = it.descricao,
                        color = Color(0xFFA09C9C)
                    )
                }

//                Exibir a quantidade de avaliacoes que o médico tem
                Text(
                    text = "${avaliacoes.size} Avaliações",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )


                if (isLoading) {
                    Text(
                        text = "Carregando avaliações...",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(350.dp)
                    ) {
                        items(avaliacoes) { avaliacao ->
                            Card(
                                elevation = CardDefaults.cardElevation(6.dp),
                                colors = CardDefaults.cardColors(Color(0xFFC6E1FF)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .padding(10.dp)
                            ) {
                                Column (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp)

                                ){
                                    Text(
                                        text = avaliacao.avaliador_nome,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Data: ${avaliacao.avaliacao_data}",
                                        fontSize = 12.sp,
                                        color = Color.Gray,
                                        modifier = Modifier.padding(top = 4.dp),
                                        textAlign = TextAlign.End
                                    )
                                    StarRating(rating = avaliacao.avaliacao_nota)

                                    Text(
                                        text = avaliacao.avaliacao_comentario,
                                        fontSize = 14.sp,
                                        color = Color(0xFF565454),
                                        modifier = Modifier.padding(top = 8.dp)
                                    )

                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(36.dp), // Espaçamento interno nas bordas
                    verticalArrangement = Arrangement.Bottom, // Move o conteúdo para a parte inferior
                    horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente
                ) {
                    Button(
                        onClick = {
                            controleDeNavegacao.navigate("telaAgendamento/${medic?.id_medico}")
                        },
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
                        Text(
                            "Selecione o dia e hora",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

        }
    }
}

