package br.senai.sp.jandira.vital.service

import br.senai.sp.jandira.vital.model.Avaliacao
import br.senai.sp.jandira.vital.model.AvaliacaoResponse
import br.senai.sp.jandira.vital.model.SexoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AvaliacaoService {
    @GET("avaliacao")
    fun listarAvaliacoes(): Call<Avaliacao>

    // Buscar pelo ID
    @GET("avaliacao/{id}")
    fun getAvaliacaoById(@Path("id") id: Int) : Call<AvaliacaoResponse>

}