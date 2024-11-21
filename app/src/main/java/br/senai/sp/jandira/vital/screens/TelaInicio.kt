package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.model.NavItem
@Composable
fun TelaInicio(controleDeNavegacao: NavHostController, idUsuario: Int) {

    val navItemList = listOf(
        NavItem("Ínicio", Icons.Default.Home),
        NavItem("Favoritos", Icons.Default.Star),
        NavItem("Carrinho", Icons.Default.ShoppingCart),
        NavItem("Notificações", Icons.Default.Notifications)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier.background(color = Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                NavigationBar(containerColor = Color.Transparent) {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                if (index == 0) {
                                    controleDeNavegacao.navigate("telaHome/$idUsuario")
                                }
                            },
                            icon = { Icon(imageVector = navItem.icon, contentDescription = "Icon") },
                            label = { Text(text = navItem.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            controleDeNavegacao = controleDeNavegacao,
            idUsuario = idUsuario
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier,
    selectedIndex: Int,
    controleDeNavegacao: NavHostController,
    idUsuario: Int
) {
    when (selectedIndex) {
        0 -> TelaHome(controleDeNavegacao, idUsuario)
        1 -> TelaFavoritos()
        2 -> TelaAdicionarCartao()
    }
}
