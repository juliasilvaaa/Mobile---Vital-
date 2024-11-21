package br.senai.sp.jandira.vital.model

data class UsuarioResponse(
    val usuario: List<Usuario>,
    val status_code: Int
)
