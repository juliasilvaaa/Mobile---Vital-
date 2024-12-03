package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.vital.model.NavItem


@Composable
fun TelaInicio(controleDeNavegacao: NavHostController, idUsuario: Int) {
    // Lista de itens da barra de navegação
    val navItemList = listOf(
        NavItem("Ínicio", Icons.Default.Home, "telaHome/$idUsuario"),
        NavItem("Favoritos", Icons.Default.Star, "telaFavoritos"),
        NavItem("Notificações", Icons.Default.Notifications, "telaNotificacoes")
    )

    // Estado do índice selecionado na barra
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            // Alterar aqui para não limpar a pilha de navegação
                            controleDeNavegacao.navigate(navItem.route) {
                                launchSingleTop = true
                                restoreState = true  // Certifique-se de que o estado não seja perdido
                            }
                        },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                        label = { Text(text = navItem.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = controleDeNavegacao,
            startDestination = "telaHome/$idUsuario",  // Garante que comece com a tela Home
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("telaHome/{idUsuario}") { backStackEntry ->
                val usuarioId = backStackEntry.arguments?.getString("idUsuario")?.toInt() ?: idUsuario
                TelaHome(controleDeNavegacao, usuarioId)
            }
            composable("telaFavoritos/{idUsuario}") { backStackEntry ->
                val usuarioId = backStackEntry.arguments?.getString("idUsuario")?.toInt() ?: idUsuario
                TelaFavoritos(controleDeNavegacao, usuarioId)
            }
            composable("telaNotificacoes") {
                TelaAdicionarCartao()  // Substitua com a tela correta
            }
        }
    }

}
