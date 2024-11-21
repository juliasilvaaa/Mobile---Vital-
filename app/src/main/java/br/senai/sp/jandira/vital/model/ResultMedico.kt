package br.senai.sp.jandira.vital.model

import com.google.gson.annotations.SerializedName

data class ResultMedico(
    @SerializedName("medico")
    val medico: List<Medicos>  // Agora, isso é uma lista, pois a API retorna um array
)