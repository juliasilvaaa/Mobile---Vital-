package br.senai.sp.jandira.vital.screens

import android.util.Log
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Especialidade
import br.senai.sp.jandira.vital.model.ResultEspecialidade
import br.senai.sp.jandira.vital.model.Usuario
import br.senai.sp.jandira.vital.model.UsuarioResponse
import br.senai.sp.jandira.vital.repository.CategoriaRepository
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun TelaHome(controleDeNavegacao: NavHostController, idUsuario: Int) {

    // Criando variaves de estado

    var id by remember {
        mutableStateOf("")
    }

    var user by remember {
        mutableStateOf<Usuario?>(null)
    }

    val categoria = CategoriaRepository().mostrarTodasAsCategorias()

    var especialidadeList = remember { mutableStateListOf<Especialidade>() }

    // Efetuar a requisicao para a API
    LaunchedEffect(key1 = Unit) {
        val callEspecialidadeList = RetrofitFactory()
            .getEspecialidadeService()
            .getAllEspecialidades()

        callEspecialidadeList.enqueue(object : Callback<ResultEspecialidade> {
            override fun onResponse(

                call: Call<ResultEspecialidade>,
                response: Response<ResultEspecialidade>
            ) {
                Log.d("API_RESPONSE", "Especialidades recebidas: ${especialidadeList.size}")

                if (response.isSuccessful) {
                    val especialidades = response.body()?.especialidades
                    especialidades?.let {
                        especialidadeList.addAll(it)
                    }
                } else{
                    Log.d("API_ERROR", "Response not successful: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<ResultEspecialidade>, t: Throwable) {
                // Lidar com a falha da requisicao
            }
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

                    // Log para garantir que a lista de usuários não está null
                    Log.i("USUARIO_RESPONSE", "Lista de usuários: $usuario")

                    if (usuario != null && usuario.isNotEmpty()) {
                        user = usuario[0]
                        Log.i("USUARIO", "Usuário carregado: ${user!!.nome}")
                    } else {
                        Log.e("USUARIO_ERROR", "Nenhum usuário encontrado ou a lista está vazia.")
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



    var especialidadeState= remember {
        mutableStateOf("")
    }

    Column {

        Surface (
            modifier = Modifier
                .height(260.dp)
        ) {
            // Fundo
            Image(
                painter = painterResource(id = R.drawable.fundo),
                contentDescription = "Fundo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                // Colocar borda arredondada

            )
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()  // Adiciona padding para evitar a sobreposição com a barra de status
                        .padding(16.dp)  // Padding interno opcional
                ) {
                    AsyncImage(
                        model = user?.foto,
                        contentDescription = "Foto do usuário",
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp)
                            .clip(RoundedCornerShape(40.dp)) // Aplica bordas arredondadas
                            .align(Alignment.TopEnd) // Posiciona no canto superior direito
                            .clickable {
                                controleDeNavegacao.navigate("telaPerfil/$idUsuario") // Substitua pelo nome da rota
                            },
                        contentScale = ContentScale.Crop
                    )
                }

                // Puxar o nome do usuario
                if (user!= null){
                    Text(
                        text = "Olá, ${user!!.nome}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = 10.dp)
                        .align(Alignment.End)
                    )
                }


            }

        }
        Spacer(modifier = Modifier.height(14.dp))

// Categorias
        Column {
            Text(
                text = "Categorias",
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color(0xFF565454),
                modifier = Modifier
                    .offset(x = 10.dp)
            )

        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyRow {
            items(categoria){ item ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .width(140.dp)
                        .offset(x = 10.dp)
                        .padding(horizontal = 6.dp)
                        .clickable {
                            // Navegacao entre as categorias
                            when (item.titulo) {
                                "Telemedicina" -> controleDeNavegacao.navigate("telaTelemedicina")
                                "Médicos" -> controleDeNavegacao.navigate("telaMedicos")
                            }
                        },
                    // Se o card estiver selecionado (true a cor dele vai ser mais escura)
                    colors = if (item.selecionado==true) CardDefaults.cardColors(containerColor = Color(0xFF2954C7))
                    // Se nao estiver selecionado a opacidade será menor
                    else CardDefaults.cardColors(containerColor = Color(0x802954C7))
                ){
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    ) {
                        // Imagem de cada categoria
                        Image(
                            painter = if (item.imagem==null)
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
        Spacer(modifier = Modifier.height(10.dp))

        Card (
            elevation = CardDefaults.cardElevation(7.dp),
            shape = RoundedCornerShape(23.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .height(48.dp)
        ){
            // Campo de Pesquisa por especialidade
            OutlinedTextField(
                value = especialidadeState.value,
                onValueChange = {
                    especialidadeState.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 0.dp),
                placeholder = {
                    Text(
                        text = "Pesquise por especialidade",
                        color = Color(0xFFA09C9C)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Search ,
                            contentDescription = "",
                            tint = Color(0xFFA09C9C)
                        )
                        
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Transparent, focusedBorderColor = Color.Transparent)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Top
        ){
            items(especialidadeList) {
                EspecialidadeCard(it, true) {}
            }

        }



    }


}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaHomePreview () {


    // Pre-visualizacao
    VitalTheme {
    }



}