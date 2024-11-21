package br.senai.sp.jandira.vital.model

import androidx.compose.ui.graphics.painter.Painter

// Criando a classe da categoria com seus atributos

data class Categoria(
    var id: Int = 0,
    var titulo: String = "",
    var imagem: Painter? = null,
    var selecionado: Boolean = false
)


