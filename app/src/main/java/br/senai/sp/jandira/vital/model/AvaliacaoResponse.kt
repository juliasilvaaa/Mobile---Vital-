package br.senai.sp.jandira.vital.model

import com.google.gson.annotations.SerializedName

data class AvaliacaoResponse(
    @SerializedName("avaliacao")
    val avaliacoes: List<Avaliacao>,
    val quantidade: Int,
    val status_code: Int
)