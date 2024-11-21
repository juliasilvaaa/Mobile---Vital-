package br.senai.sp.jandira.vital.model

import com.google.firebase.firestore.auth.Token

data class UsuarioLogin(
    val id_usuario: Int = 0,
    val nome: String,
    val token: String
)