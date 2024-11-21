package br.senai.sp.jandira.vital.model

data class SexoResponse(
    val sexos: List<Sexo>,
    val quantidade: Int,
    val status_code: Int
)
