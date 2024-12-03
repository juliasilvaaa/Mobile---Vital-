package br.senai.sp.jandira.vital.service

import br.senai.sp.jandira.vital.model.ResultVideo
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {
    // Funcao para listar todos os videos
    @GET("video")
    fun getAllVideos(): Call<ResultVideo>

}