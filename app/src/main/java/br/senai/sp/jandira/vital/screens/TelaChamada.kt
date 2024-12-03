package br.senai.sp.jandira.vital.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import br.senai.sp.jandira.vital.service.RetrofitFactory

@Composable
fun TelaChamada(controleDeNavegacao: NavHostController) {
    val context = LocalContext.current // Obtendo o contexto local

    val url = remember { mutableStateOf<String?>(null) }

    // Chamar a API para gerar a URL da reunião Jitsi
    LaunchedEffect(Unit) {
        try {
            val response = RetrofitFactory().getApiService().generateMeeting()
            url.value = response.url
        } catch (e: Exception) {
            // Tratar erro de requisição, se necessário
            e.printStackTrace()
        }
    }

    // Abrir a URL no navegador ou embutir o Jitsi, conforme sua escolha
    url.value?.let {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        // Agora, usamos o LocalContext para iniciar a activity
        context.startActivity(intent)
    }

    // Mensagem de carregamento enquanto espera a URL
    if (url.value == null) {
        Text("Carregando chamada...")
    }
}
