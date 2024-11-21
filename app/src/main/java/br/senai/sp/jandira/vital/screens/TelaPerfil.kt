package br.senai.sp.jandira.vital.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.model.Usuario
import br.senai.sp.jandira.vital.model.UsuarioResponse
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TelaPerfil(controleDeNavegacao: NavHostController, idUsuario: Int) {
    // Variáveis de estado
    var user by remember { mutableStateOf<Usuario?>(null) }

    // Requisição da API
    LaunchedEffect(key1 = idUsuario) {
        val callUsuario = RetrofitFactory()
            .getUsuarioService()
            .getUsuarioById(idUsuario)

        callUsuario.enqueue(object : Callback<UsuarioResponse> {
            override fun onResponse(call: Call<UsuarioResponse>, response: Response<UsuarioResponse>) {
                if (response.isSuccessful) {
                    val usuario = response.body()?.usuario
                    if (usuario != null && usuario.isNotEmpty()) {
                        user = usuario[0]
                    } else {
                        Log.e("USUARIO_ERROR", "Nenhum usuário encontrado.")
                    }
                } else {
                    Log.e("USUARIO_ERROR", "Erro ao carregar usuário: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                Log.e("USUARIO_ERROR", "Falha ao buscar usuário: ${t.message}")
            }
        })
    }

    VitalTheme {
        Surface {
            // Header
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF2954C7),
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    AsyncImage(
                        model = user?.foto,
                        contentDescription = "Foto do usuário",
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .clip(RoundedCornerShape(40.dp))
                            .clickable {
                                controleDeNavegacao.navigate("telaPerfil")
                            },
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    if (user != null) {
                        Text(
                            text = "${user!!.nome}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Lista de informações
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 260.dp)
            ) {
                listOf(
                    Pair(Icons.Filled.Person, user?.nome ?: "Nome não disponível"),
                    Pair(Icons.Filled.AccountBox, user?.cpf ?: "CPF não disponível"),
                    Pair(Icons.Filled.Email, user?.email ?: "E-mail não disponível"),
                    Pair(Icons.Filled.DateRange, user?.data_nascimento ?: "Data de nascimento não disponível"),
                    Pair(Icons.Filled.Lock, "Alterar Senha"),
                    Pair(Icons.Filled.Notifications, "Ativar Notificações"),
                    Pair(Icons.Filled.ExitToApp, "Sair")
                ).forEach { (icon, text) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .clickable {
                                when (text) {
                                    "Alterar Senha" -> controleDeNavegacao.navigate("telaAlterarSenha")
                                    "Ativar Notificações" -> {/* Implementar ação */}
                                    "Sair" -> controleDeNavegacao.navigate("telaLogin")
                                }
                            }
                            .padding(16.dp) // Espaçamento interno
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color(0xFF2954C7),
                                modifier = Modifier.padding(end = 16.dp) // Espaço entre ícone e texto
                            )
                            Text(
                                text = text,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
