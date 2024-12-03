
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Videos

@Composable
fun GaleriaDeVideo(videos: List<Videos>, navController: NavHostController) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        // Título da galeria
        Text(
            text = "Galeria de Vídeos",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(videos) { video ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .size(width = 160.dp, height = 100.dp)
                        .clickable {
                            // Corrigido para navegar usando a URL do vídeo
                            navController.navigate("video_screen/${video.url_video}")
                        }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Aqui você pode adicionar a miniatura do vídeo, por exemplo:
                        Image(
                            painter = painterResource(id = R.drawable.placeholder), // Substitua por uma imagem de miniatura
                            contentDescription = video.titulo_video,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
