
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.vital.model.Consultas
import br.senai.sp.jandira.vital.model.ResultConsultas
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*


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
                    _consultas.value = response.body()?.consultas ?: emptyList()
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

    val nomesDosMeses = listOf(
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    )

    // Atualiza o calendário quando o mês é alterado
    val diasNoMes = remember(indiceMesAtual, anoAtual) {
        getDiasDoMes(indiceMesAtual, anoAtual)
    }

    // Buscar consultas do médico
    LaunchedEffect(idMedico) {
        viewModel.buscarConsultasMedico(idMedico)
    }

    VitalTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xffC6E1FF)),
            verticalArrangement = Arrangement.Bottom
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Voltar",
                tint = Color(0xFF565454),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable {
                        controleDeNavegacao.navigate("infoMedicos")
                    }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    "Escolha o dia",
                    fontSize = 12.sp,
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
                        }
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Mês Anterior")
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "${nomesDosMeses[indiceMesAtual].uppercase()} $anoAtual",
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
                    }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Próximo Mês")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
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

                    // Filtra os horários disponíveis para o dia selecionado
                    val horarios = viewModel.consultas.value.filter {
                        it.query_days == "2024-${String.format("%02d", indiceMesAtual + 1)}-${String.format("%02d", dia)}"
                    }.map { it.horas_consulta }

                    horarios.chunked(4).forEach { linhaHorarios ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            linhaHorarios.forEach { horario ->
                                HorarioCard(horario)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
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
    Column(
        modifier = Modifier
            .width(60.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = diaSemana, fontSize = 12.sp, color = Color.Gray)
        Text(text = dia, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun HorarioCard(horario: String) {
    Box(
        modifier = Modifier
            .width(60.dp)
            .background(color = Color(0xff0174DE), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = horario, fontSize = 12.sp, color = Color.White)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgendamentoPreview() {
    VitalTheme {

    }
}
