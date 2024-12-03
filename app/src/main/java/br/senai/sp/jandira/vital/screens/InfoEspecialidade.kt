package br.senai.sp.jandira.vital.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.model.Especialidade
import br.senai.sp.jandira.vital.model.ResultEspecialidade
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun InfoEspecialidade(controleDeNavegacao: NavHostController, idEspecialidade: String?) {


    val idEspecialidadeInt = idEspecialidade?.toIntOrNull() ?: 0
    var especialidade by remember { mutableStateOf<Especialidade?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // Chamada para a API
    LaunchedEffect(key1 = idEspecialidadeInt) {
        Log.d("InfoEspecialidade", "ID da especialidade enviado: $idEspecialidadeInt")

        val callEspecialidade = RetrofitFactory()
            .getEspecialidadeService()
            .getEspecialidadeById(idEspecialidadeInt)

        callEspecialidade.enqueue(object : Callback<ResultEspecialidade> {
            override fun onResponse(call: Call<ResultEspecialidade>, response: Response<ResultEspecialidade>) {
                if (response.isSuccessful) {
                    val especialidadeResponse = response.body()?.especialidades
                    if (especialidadeResponse.isNullOrEmpty()) {
                        Log.d("InfoEspecialidade", "Nenhuma especialidade encontrada ou especialidades são nulas.")
                    } else {
                        especialidade = especialidadeResponse.first()
                        Log.d("InfoEspecialidade", "Especialidade recebida: ${especialidade?.nome}, ID: ${especialidade?.id_especialidade}")
                    }
                } else {
                    Log.d("InfoEspecialidade", "Erro na resposta da API: ${response.code()}")
                }
                isLoading = false
            }



            override fun onFailure(call: Call<ResultEspecialidade>, t: Throwable) {
                // Log para falha de requisição
                Log.d("InfoEspecialidade", "Falha na requisição: ${t.message}")
                isLoading = false
            }
        })
    }

    VitalTheme {
        Surface {
            Column (
                modifier = Modifier
                    .background(color = Color.Red)
            ){
                especialidade?.let {
                    Text(
                        text = "Dr. ${it.nome}"
                    )

                    Text(
                        text = it.descricao
                    )
                }
            }
        }
    }
}
