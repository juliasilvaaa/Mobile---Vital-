package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun VideoScreen(navController: NavHostController, videoUrl: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (videoUrl != null) {
            // Aqui você pode colocar um WebView ou apenas exibir a URL
            Text(text = "Reproduzindo o vídeo em: $videoUrl")
        } else {
            Text(text = "URL do vídeo não encontrado")
        }
    }
}
