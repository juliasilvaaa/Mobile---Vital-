package br.senai.sp.jandira.vital.model

data class Medicos(
    // Atributos dos medicos
    var id_medico: Int = 0,
    var nome_medico: String = "",
    var email_medico: String = "",
    var telefone_medico: String = "",
    var crm: String = "",
    var data_nascimento_medico: String = "",
    var foto_medico: String,
    var descricao: String = "",
    var especialidade: String = ""
)
