package br.senai.sp.jandira.vital.model

data class Consultas(
    val id_consulta: Int,
    val detalhes_consulta: String,
    val dias_consulta: String,
    val horas_consulta: String,
    val data_formatada: String,
    val hora_formatada: String,
    val nome_medico: String,
    val nome_especialidade: String,
    val nome_empresa: String
)