package br.senai.sp.jandira.vital.model

data class Consultas(
val query_id: Int,
val query_details: String,
val query_days: String,
val horas_consulta: String,
val nome_medico: String,
val nome_especialidade: String,
val nome_empresa: String
)
