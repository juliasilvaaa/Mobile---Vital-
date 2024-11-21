package br.senai.sp.jandira.vital.model

import androidx.compose.ui.graphics.painter.Painter

// Atributos das Notificacoes

data class Notificacoes(
    var id: Int = 0,
    var titulo: String = "",
    var imagem: Painter? = null,
    var descricao: String = ""
)

