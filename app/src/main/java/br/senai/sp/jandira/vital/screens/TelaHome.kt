package br.senai.sp.jandira.vital.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Especialidade
import br.senai.sp.jandira.vital.model.NavItem
import br.senai.sp.jandira.vital.model.ResultEspecialidade
import br.senai.sp.jandira.vital.model.Usuario
import br.senai.sp.jandira.vital.model.UsuarioResponse
import br.senai.sp.jandira.vital.repository.CategoriaRepository
import br.senai.sp.jandira.vital.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TelaHome(controleDeNavegacao: NavHostController, idUsuario: Int) {
    var id by remember { mutableStateOf("") }
    var user by remember { mutableStateOf<Usuario?>(null) }
    val categoria = CategoriaRepository().mostrarTodasAsCategorias()
    var especialidadeList = remember { mutableStateListOf<Especialidade>() }

    // Requisição da API
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
                    especialidades?.let {
                        especialidadeList.addAll(it)
                    }
                }
            }

            override fun onFailure(call: Call<ResultEspecialidade>, t: Throwable) {}
        })
    }

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
                    }
                }
            }

            override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {}
        })
    }

    var especialidadeState = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val navItemList = listOf(
                    NavItem("Ínicio", Icons.Default.Home, "telaHome/$idUsuario"),
                    NavItem("Favoritos", Icons.Default.Star, "telaFavoritos/$idUsuario"),
                    NavItem("Notificações", Icons.Default.Notifications, "telaNotificacoes")
                )

                var selectedIndex by rememberSaveable { mutableStateOf(0) }

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
        ) {
            // O conteúdo da tela Home vai aqui
            Surface(modifier = Modifier.height(260.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.fundo),
                    contentDescription = "Fundo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(16.dp)
                    ) {
                        AsyncImage(
                            model = user?.foto,
                            contentDescription = "Foto do usuário",
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                                .clip(RoundedCornerShape(40.dp))
                                .align(Alignment.TopEnd)
                                .clickable {
                                    controleDeNavegacao.navigate("telaPerfil/$idUsuario")
                                },
                            contentScale = ContentScale.Crop
                        )
                    }

                    if (user != null) {
                        Text(
                            text = "Olá, ${user!!.nome}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .offset(y = 20.dp, x = -10.dp)
                                .align(Alignment.End)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Categorias",
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color(0xFF565454),
                modifier = Modifier.offset(x = 10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow {
                items(categoria) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .width(140.dp)
                            .offset(x = 10.dp)
                            .padding(horizontal = 6.dp)
                            .clickable {
                                when (item.titulo) {
                                    "Telemedicina" -> controleDeNavegacao.navigate("telaTelemedicina/$idUsuario")
                                    "Médicos" -> controleDeNavegacao.navigate("telaMedicos/$idUsuario")
                                    "Galeria" -> controleDeNavegacao.navigate("telaGaleria")
                                    "Consultas" -> controleDeNavegacao.navigate("telaHistorico/$idUsuario")
                                }
                            },
                        colors = if (item.selecionado == true) CardDefaults.cardColors(containerColor = Color(0xFF2954C7))
                        else CardDefaults.cardColors(containerColor = Color(0x802954C7))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = if (item.imagem == null)
                                    painterResource(id = R.drawable.notimage) else item.imagem!!,
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(45.dp)
                            )
                            Text(
                                text = item.titulo,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

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
                    EspecialidadeCard(
                        controleDeNavegacao = controleDeNavegacao, // Passe o controlador de navegação
                        especialidade = especialidade,            // O item atual da lista
                        isFavorito = true,                        // Sempre favorito nesta tela
                        onFavoritoClick = {}                      // Clique no favorito sem ação por enquanto
                    )
                    }
                    }
                    }
                    }
                    }
