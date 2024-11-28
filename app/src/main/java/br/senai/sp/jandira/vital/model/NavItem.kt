package br.senai.sp.jandira.vital.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,  // Alterado para 'route' para refletir o nome correto
)