package br.senai.sp.jandira.vital.model

data class ResultConsultas(
    val consultas: List<Consultas>,
    val quantidade: Int
)