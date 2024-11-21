package br.senai.sp.jandira.vital.model

import com.google.gson.annotations.SerializedName



data class ResultMedicos(
    @SerializedName("medicos") // Mapeia o campo "medicos" do JSON para esta propriedade
    val medico: List<Medicos>
)
