package br.senai.sp.jandira.vital.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.model.ItemNav
import br.senai.sp.jandira.vital.model.NavItem
import br.senai.sp.jandira.vital.model.Usuario

@Composable
fun TelaFavoritos(controleDeNavegacao: NavHostController, idUsuario: Int) {

    var id by remember { mutableStateOf("") }
    var user by remember { mutableStateOf<Usuario?>(null) }
    // Criar a lista de Itens
    val navItemList = listOf(
        ItemNav("Médicos"),
        ItemNav("Especialidades")
    )

    var selectedIndex by remember { mutableStateOf(0) }

    // Suponha uma lista de favoritos para a demonstração
    val favoritosEspecialidades = listOf("Cardiologia", "Pediatria", "Dermatologia")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val navItemList = listOf(
                    NavItem("Ínicio", Icons.Default.Home, "telaHome/$idUsuario"),
                    NavItem("Favoritos", Icons.Default.Star, "telaFavoritos/$idUsuario"),
                    NavItem("Notificações", Icons.Default.Notifications, "telaNotificacoes")
                )

                var selectedIndex by rememberSaveable { mutableStateOf(1) }

                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            controleDeNavegacao.navigate(navItem.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                        label = { Text(text = navItem.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()) // Tirando o espaco antes do conteúdo
        ){

            // Conteudo Tela Favoritos
            Surface {
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFF2954C7),
                            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 50.dp)
                ) {
                    Text(
                        "Favoritos",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )

                    // NavigationBar dentro da Box
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 16.dp) // Espaço abaixo da barra
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            navItemList.forEachIndexed { index, item ->
                                Box(
                                    modifier = Modifier
                                        .clickable {
                                            selectedIndex = index
                                        }
                                        .padding(1.dp)
                                        .background(
                                            if (index == selectedIndex) Color(0xFFFF6B00) else Color.Transparent,
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .width(140.dp)
                                        .height(40.dp)
                                ) {
                                    Text(
                                        text = item.label,
                                        color = Color.White,
                                        modifier = Modifier.align(Alignment.Center),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(top = 200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    // Exibe o conteúdo com base no índice selecionado
                    when (selectedIndex) {
                        0 -> TelaMedicosFavoritos()
                        1 -> TelaEspecialidadesFav(favoritos = favoritosEspecialidades)
                    }
                }
            }
        }
    }


}

@Composable
fun TelaMedicosFavoritos() {
    // Sua implementação para mostrar médicos favoritos
    Text("Tela de Médicos Favoritos")
}

@Composable
fun TelaEspecialidadesFav(favoritos: List<String>) {
    // Implementação para mostrar especialidades favoritas
    Column {
        Text("Especialidades Favoritas", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        favoritos.forEach { especialidade ->
            Text(text = especialidade, fontSize = 16.sp)
        }
    }
}
