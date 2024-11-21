package br.senai.sp.jandira.vital.service

import br.senai.sp.jandira.vital.model.ResultEspecialidade
import br.senai.sp.jandira.vital.model.UsuarioResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface EspecialidadeService {

    // Funcao para listar todas as especialidades
    @GET("especialidade")
    fun getAllEspecialidades(): Call<ResultEspecialidade>  // Esse call vai me devolver uma lista de personagens

    // Funcao para listar a especialidade por ID
    @GET("especialidade/{id}")
    fun getEspecialidadeById(@Path("id") id: Int) : Call<ResultEspecialidade>

}