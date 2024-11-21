package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.vital.model.Especialidade
import br.senai.sp.jandira.vital.ui.theme.VitalTheme

@Composable
fun TelaEspecialidadesFav(favoritos: List<Especialidade>) {
    VitalTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEAEAEA))
        ) {
            Text(
                text = "Especialidades Favoritas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(favoritos) { especialidade ->
                    EspecialidadeCard(
                        especialidade = especialidade,
                        isFavorito = true, // Sempre favorito aqui
                        onFavoritoClick = {} // Não há remoção de favoritos nesta tela
                    )
                }
            }
        }
    }
}
