package br.senai.sp.jandira.vital.model

data class Videos(
    val id_video: Int,
    val titulo_video: String,
    val descricao_video: String,
    val url_video: String,
    val data_publicacao: String,
    val id_empresa: Int,
    val nome_empresa: String,
    val cnpj: String,
    val email_empresa: String,
    val telefone_empresa: String,
    val telefone_clinica: String
)
