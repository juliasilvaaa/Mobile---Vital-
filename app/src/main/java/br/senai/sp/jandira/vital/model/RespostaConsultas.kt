package br.senai.sp.jandira.vital.model

data class RespostaConsultas(
    val medico: List<Consultas>,
    val status_code: Int
)