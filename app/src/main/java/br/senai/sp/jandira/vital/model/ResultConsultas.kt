package br.senai.sp.jandira.vital.model

data class ResultConsultas(

    // Mapeando a resposta que contem na lista de consultas
    val consultas: List<Consultas>,
    val quantidade: Int
)
