package br.senai.sp.jandira.vital.service

import br.senai.sp.jandira.vital.model.AvaliacaoResponse
import br.senai.sp.jandira.vital.model.ResultConsultas
import br.senai.sp.jandira.vital.model.ResultMedico
import br.senai.sp.jandira.vital.model.ResultMedicos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MedicoService {

    // Funcao para listar todas as especialidades
    @GET("medico")
    fun getAllMedicos(): Call<ResultMedicos>  // Esse call vai me devolver uma lista de medicos

    @GET("medico/{id}")
    fun getMedicoById(@Path("id") id: Int) : Call<ResultMedico>

    @GET("avaliacao/medico/{id}")
    fun getAvaliacoesMedico(@Path("id") idMedico: Int): Call<AvaliacaoResponse>


    @GET("consulta/medico/{id}")
    fun getConsultasMedico(@Path("id") idMedico: String?): retrofit2.Call<ResultConsultas>



}