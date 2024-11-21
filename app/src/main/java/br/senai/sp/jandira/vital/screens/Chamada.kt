//import android.content.Context
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import org.jitsi.meet.sdk.JitsiMeet
//import org.jitsi.meet.sdk.JitsiMeetActivity
//import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
//import java.net.URL
//
//@Composable
//fun VideoCallScreen(context: Context) {
//    // Configuração inicial do Jitsi Meet
//    val serverURL = URL("https://meet.jit.si")
//    val defaultOptions = JitsiMeetConferenceOptions.Builder()
//        .setServerURL(serverURL)
//        .setWelcomePageEnabled(false)  // Desabilita a página de boas-vindas
//        .build()
//
//    // Definir as opções padrão para conferências
//    JitsiMeet.setDefaultConferenceOptions(defaultOptions)
//
//    // Interface de usuário
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Iniciar Videoconferência",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Button(
//            onClick = {
//                // Configura as opções para a conferência de vídeo
//                val options = JitsiMeetConferenceOptions.Builder()
//                    .setRoom("NomeDaSalaUnico")  // Nome da sala de conferência
//                    .setAudioMuted(false)         // Habilita áudio
//                    .setVideoMuted(false)         // Habilita vídeo
//                    .build()
//
//                // Lança a atividade do Jitsi Meet
//                JitsiMeetActivity.launch(context, options)
//            }
//        ) {
//            Text(text = "Iniciar Chamada de Vídeo")
//        }
//    }
//}
