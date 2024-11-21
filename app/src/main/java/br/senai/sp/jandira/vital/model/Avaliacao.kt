package br.senai.sp.jandira.vital.model


data class Avaliacao(
    val id_avaliacao: Int,
    val avaliacao_nota: Int,
    val avaliacao_comentario: String,
    val avaliacao_data: String,
    val avaliador_nome: String,
    val avaliador_email: String
)