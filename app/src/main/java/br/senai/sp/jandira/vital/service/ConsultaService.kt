package br.senai.sp.jandira.vital.service

import android.telecom.Call
import br.senai.sp.jandira.vital.model.AvaliacaoResponse
import br.senai.sp.jandira.vital.model.ResultConsultas
import retrofit2.http.GET
import retrofit2.http.Path

interface ConsultaService {


    @GET("consultas")
    fun getAllConsultas(): retrofit2.Call<ResultConsultas>

    @GET("consulta/medico/{id}")
    fun getConsultasMedico(@Path("id") idMedico: Int): retrofit2.Call<ResultConsultas>

}