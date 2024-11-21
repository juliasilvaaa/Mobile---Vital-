package br.senai.sp.jandira.vital.model

data class JitsiMeetRoom(
    val name: String,
    val participants: List<String> = emptyList(),
    val startTime: Long = System.currentTimeMillis()
)

