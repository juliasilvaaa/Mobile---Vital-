package br.senai.sp.jandira.vital.service

import android.telecom.Call
import br.senai.sp.jandira.vital.model.ResultConsultas
import retrofit2.http.GET

interface ConsultaService {


    @GET("consultas")
    fun getAllConsultas(): retrofit2.Call<ResultConsultas>
}