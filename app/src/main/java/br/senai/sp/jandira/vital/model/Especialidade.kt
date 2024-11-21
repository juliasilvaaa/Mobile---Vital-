package br.senai.sp.jandira.vital.model

import androidx.compose.ui.graphics.painter.Painter

data class Especialidade(
    // Atributos das especialidades
    var id_especialidade : Int = 0,
    var nome: String = "",
    var descricao: String = "",
    var imagem_url: String
)
