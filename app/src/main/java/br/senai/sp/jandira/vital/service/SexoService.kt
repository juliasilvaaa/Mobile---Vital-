package br.senai.sp.jandira.vital.service

import br.senai.sp.jandira.vital.model.Sexo
import br.senai.sp.jandira.vital.model.SexoResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path


interface SexoService {
    @GET("sexo")
    fun listarSexos(): Call<SexoResponse>

    // Buscar pelo ID - result do usuario
    @GET("sexo/{id}")
    fun getSexoById(@Path("id") id: Int) : Call<SexoResponse>

}