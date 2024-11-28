package br.senai.sp.jandira.vital.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.model.Especialidade
import br.senai.sp.jandira.vital.model.ResultEspecialidade
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun TelaTelemedicina(controleDeNavegacao: NavHostController, idUsuario : Int) {
    // Lista de especialidades
    val especialidadeList = remember { mutableStateListOf<Especialidade>() }

    // Efetuar a requisição para a API
    LaunchedEffect(key1 = Unit) {
        val callEspecialidadeList = RetrofitFactory()
            .getEspecialidadeService()
            .getAllEspecialidades()

        callEspecialidadeList.enqueue(object : Callback<ResultEspecialidade> {
            override fun onResponse(
                call: Call<ResultEspecialidade>,
                response: Response<ResultEspecialidade>
            ) {
                if (response.isSuccessful) {
                    val especialidades = response.body()?.especialidades
                    especialidades?.let { especialidadeList.addAll(it) }
                } else {
                    Log.d("API_ERROR", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResultEspecialidade>, t: Throwable) {
                Log.e("API_ERROR", "Falha ao carregar especialidades: ${t.message}")
            }
        })
    }

    VitalTheme {
        Surface {
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF2954C7),
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(top = 50.dp)
            ) {
                // Icone de voltar
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .clickable { controleDeNavegacao.navigate("telaInicio/$idUsuario") }
                )

                // Título
                Text(
                    "Telemedicina",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Column(
                modifier = Modifier.padding(top = 180.dp)
            ) {
                // Campo de Pesquisa
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .width(360.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color(0xFFF6F6F6)),
                    placeholder = {
                        Text(
                            text = "Pesquise por especialidade",
                            color = Color(0xFFA09C9C)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar",
                                tint = Color(0xFFA09C9C)
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent
                    )
                )

                // Lista de Especialidades
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(especialidadeList) { especialidade ->
                        // Gerenciar o estado do favorito
                        val isFavorito = remember { mutableStateOf(false) }

                        EspecialidadeCard(
                            especialidade = especialidade,
                            isFavorito = isFavorito.value,
                            onFavoritoClick = { isFavorito.value = !isFavorito.value }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EspecialidadeCard(
    especialidade: Especialidade,
    isFavorito: Boolean,
    onFavoritoClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(150.dp)
            .height(150.dp)
    ) {
        Box {
            // Imagem
            AsyncImage(
                model = especialidade.imagem_url,
                contentDescription = "Imagem da especialidade",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Sobreposição para texto
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            // Nome da especialidade
            Text(
                text = especialidade.nome,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
            )

            // Ícone de favorito
            Icon(
                imageVector = if (isFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = if (isFavorito) "Desfavoritar" else "Favoritar",
                tint = if (isFavorito) Color.Red else Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clickable { onFavoritoClick() }
            )
        }
    }
}

