
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.vital.model.Consultas
import br.senai.sp.jandira.vital.model.Medicos
import br.senai.sp.jandira.vital.model.ResultConsultas
import br.senai.sp.jandira.vital.model.ResultMedico
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import androidx.compose.ui.Alignment



class AgendamentoViewModel : ViewModel() {
    private val _consultas = mutableStateOf<List<Consultas>>(emptyList())
    val consultas: State<List<Consultas>> = _consultas

    // Função para buscar as consultas do médico
    fun buscarConsultasMedico(idMedico: String?) {
        val apiService = RetrofitFactory().getMedicoService()  // Usando o método correto
        apiService.getConsultasMedico(idMedico).enqueue(object : Callback<ResultConsultas> {
            override fun onResponse(
                call: Call<ResultConsultas>,
                response: Response<ResultConsultas>
            ) {
                if (response.isSuccessful) {
                    _consultas.value = response.body()?.medico ?: emptyList()
                }
            }

            override fun onFailure(call: Call<ResultConsultas>, t: Throwable) {
        // Tratar erro
            }

        })
    }
}


@Composable
fun Agendamento(controleDeNavegacao: NavHostController, idMedico: String?) {
    val viewModel: AgendamentoViewModel = viewModel()
    var indiceMesAtual by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    var anoAtual by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }
    var dataSelecionada by remember { mutableStateOf<Pair<String, Int>?>(null) }

    // Selecionar dia  e hora
    var horarioSelecionado by remember { mutableStateOf<String?>(null) }
    var isHorarioSelecionado by remember { mutableStateOf(false) }

    val nomesDosMeses = listOf(
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    )

    // Atualiza o calendário quando o mês é alterado
    val diasNoMes = remember(indiceMesAtual, anoAtual) {
        getDiasDoMes(indiceMesAtual, anoAtual)
    }


    // Buscar informacoes do Medico
    val idMedicoInt = idMedico?.toIntOrNull() ?: 0

    var medic by remember { mutableStateOf<Medicos?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Requisicao
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
    }


    // Buscar consultas do médico
    LaunchedEffect(idMedico) {
        viewModel.buscarConsultasMedico(idMedico)
    }

    VitalTheme {
        Surface {
            Column {
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFFC6E1FF),
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .padding(bottom = 16.dp)
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
                            .clickable { controleDeNavegacao.navigate("infoMedico/$idMedico") }
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
                                text = "Dr. ${it.nome_medico}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = it.especialidade,
                                color = Color(0xFFA09C9C),
                                fontSize = 14.sp
                            )
                        }

                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                        )
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        "Escolha o dia",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff565454),
                        modifier = Modifier.padding(start = 25.dp, top = 25.dp, bottom = 3.dp)
                    )

                    // Navegação do mês
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                if (indiceMesAtual > 0) {
                                    indiceMesAtual--
                                } else {
                                    indiceMesAtual = 11
                                    anoAtual--
                                }
                            },
                            modifier = Modifier
                                .background(Color.Transparent),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Mês Anterior",
                                tint = Color(0xFF565454)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))

                        // Mudar de Mes
                        Text(
                            text = "${nomesDosMeses[indiceMesAtual].capitalize(Locale.getDefault())} - $anoAtual",
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        Button(onClick = {
                            if (indiceMesAtual < 11) {
                                indiceMesAtual++
                            } else {
                                indiceMesAtual = 0
                                anoAtual++
                            }
                        },
                            modifier = Modifier
                                .background(Color.Transparent),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Icon(
                                Icons.Default.ArrowForward,
                                contentDescription = "Próximo Mês",
                                tint = Color(0xFF565454)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(diasNoMes) { (diaSemana, dia) ->
                            DiaCard(
                                diaSemana = diaSemana,
                                dia = dia.toString(),
                                onClick = { dataSelecionada = diaSemana to dia }
                            )
                        }


                    }

                    // Exibição de horários disponíveis se uma data for selecionada
                    dataSelecionada?.let { (_, dia) ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Horários disponíveis para $dia",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        val dataSelecionadaFormatada =
                            String.format("%02d/%02d/%d", dia, indiceMesAtual + 1, anoAtual)

                        // Filtra os horários disponíveis para o dia selecionado usando a data formatada
                        val horarios = viewModel.consultas.value.filter {
                            it.data_formatada == dataSelecionadaFormatada
                        }.map { it.hora_formatada }

                    Column (
                        modifier = Modifier
                            .height(180.dp)
                    ){
                            horarios.chunked(3).forEach { linhaHorarios ->
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(4), // Define 4 itens por linha
                                    modifier = Modifier
                                        .padding(14.dp)
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(8.dp), // Espaçamento vertical entre as linhas
                                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento horizontal entre as colunas
                                ) {
                                    items(horarios) { horario ->
                                        HorarioCard(horario, horarioSelecionado) { horarioClicado ->
                                            horarioSelecionado = horarioClicado
                                            isHorarioSelecionado = true
                                        }
                                    }
                                }


                            }
                        }
                    }


                    Column(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                            ){



                        // Botao para direcionar para o pagamento
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(36.dp), // Espaçamento interno nas bordas
                            verticalArrangement = Arrangement.Bottom, // Move o conteúdo para a parte inferior
                            horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente
                        ) {
                            Button(
                                onClick = {
                                    // Direcionar para a tela de pagamento após selecionar o dia e a hora
                                    if (isHorarioSelecionado && horarioSelecionado != null) {
                                        controleDeNavegacao.navigate("telaMetodos/$horarioSelecionado")
                                    }
                                },
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(300.dp)
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
                                Text(text = "Ir para Pagamento")
                            }
                        }

                    }

                }
            }
        }
    }
}

fun getDiasDoMes(mes: Int, ano: Int): List<Pair<String, Int>> {
    val dias = mutableListOf<Pair<String, Int>>()
    val primeiroDiaDoMes = LocalDate.of(ano, mes + 1, 1)
    val ultimoDia = primeiroDiaDoMes.lengthOfMonth()

    for (dia in 1..ultimoDia) {
        val data = LocalDate.of(ano, mes + 1, dia)
        val diaSemana = data.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("pt", "BR"))
        dias.add(diaSemana to dia)
    }
    return dias
}

@Composable
fun DiaCard(diaSemana: String, dia: String, onClick: () -> Unit) {

    var selecionado by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .width(60.dp)
            .height(70.dp)
            .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
            .background(
                if (selecionado) Color(0xFF0174DE) else Color(0xFFBCBCBC),
                RoundedCornerShape(10.dp)
            )
            .clickable {
                selecionado = !selecionado
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = diaSemana, fontSize = 12.sp, color = Color.White)
        Text(text = dia, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
fun HorarioCard(horario: String, horarioSelecionado: String?, onHorarioClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .background(
                color = if (horarioSelecionado == horario) Color(0xFF0174DE) else Color.Transparent,
                RoundedCornerShape(10.dp)
            )
            .border(width = 2.dp, color = Color(0xFFBCBCBC), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 8.dp)
            .clickable {
                onHorarioClick(horario)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = horario, fontSize = 12.sp, color = if (horarioSelecionado == horario) Color.White else Color.Black)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgendamentoPreview() {
    VitalTheme {

    }
}
