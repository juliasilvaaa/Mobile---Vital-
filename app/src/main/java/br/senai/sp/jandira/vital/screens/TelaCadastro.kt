package br.senai.sp.jandira.vital.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Sexo
import br.senai.sp.jandira.vital.model.SexoResponse
import br.senai.sp.jandira.vital.model.Usuario
import br.senai.sp.jandira.vital.service.RetrofitFactory
import br.senai.sp.jandira.vital.service.SexoService
import br.senai.sp.jandira.vital.ui.theme.VitalTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastro(controleDeNavegacao: NavHostController) {
    val context = LocalContext.current


    val usuarioService = RetrofitFactory().getUserService()
    val sexoService = RetrofitFactory().getSexoService(SexoService::class.java)

    var nomeState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var cpfState by remember { mutableStateOf("") }
    var sexoSelecionadoState by remember { mutableStateOf(0) }
    var senhaState by remember { mutableStateOf("") }
    var confirmarSenhaState by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var listaSexos by remember { mutableStateOf(listOf<Sexo>()) }


    fun validarCampos(): Boolean {
        if (nomeState.isBlank()) {
            Toast.makeText(context, "Nome é obrigatório.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (emailState.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailState).matches()) {
            Toast.makeText(context, "Email inválido.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (cpfState.length != 11 || cpfState.any { !it.isDigit() }) {
            Toast.makeText(context, "CPF inválido.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (senhaState != confirmarSenhaState) {
            Toast.makeText(context, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (selectedDate.isBlank()) {
            Toast.makeText(context, "Data de nascimento é obrigatória.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }



    // Função para salvar o usuário
    fun salvarUsuario() {
        if (senhaState == confirmarSenhaState) {
            val usuario = Usuario(
                nome = nomeState,
                email = emailState,
                cpf = cpfState,
                id_sexo = sexoSelecionadoState,
                senha = senhaState,
                data_nascimento = selectedDate,
                foto = "",
                isOver = false
            )

            usuarioService.salvarUsuario(usuario).enqueue(object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        controleDeNavegacao.navigate("telaLogin")
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Toast.makeText(context, "Erro: ${response.code()} - $errorBody", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(context, "Falha: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            })

        } else {
            Toast.makeText(context, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
        }
    }


    // Carregar os dados de sexo ao abrir a tela
    LaunchedEffect(Unit) {
        sexoService.listarSexos().enqueue(object : Callback<SexoResponse> {
            override fun onResponse(call: Call<SexoResponse>, response: Response<SexoResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        listaSexos = responseBody.sexos
                    } else {
                        Toast.makeText(context, "Erro: Resposta vazia", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Erro ao carregar sexos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SexoResponse>, t: Throwable) {
                Toast.makeText(context, "Falha ao buscar dados de sexo", Toast.LENGTH_SHORT).show()
            }
        })
    }

    VitalTheme {
        Column {
            Surface(
                modifier = Modifier
                    .height(220.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.onda),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        modifier = Modifier
                            .width(140.dp)
                            .height(110.dp)
                    )
                }
                }

            Column(
                modifier = Modifier.padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Criar Conta", fontSize = 28.sp, color = Color(0xFF2954C7))

                Spacer(modifier = Modifier.height(10.dp))

                // Campo Nome
                OutlinedTextField(
                    value = nomeState,
                    onValueChange = { nomeState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nome") },
                    shape = RoundedCornerShape(26.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email") },
                    shape = RoundedCornerShape(26.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Email, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = cpfState,
                    onValueChange = { cpfState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "CPF") },
                    shape = RoundedCornerShape(26.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Person, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Data de Nascimento") },
                    shape = RoundedCornerShape(26.dp),
                    visualTransformation = DateVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = {
                        Icon(Icons.Filled.DateRange, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))


                var sexoSelecionadoDescricao by remember {
                    mutableStateOf("Selecione o sexo")
                }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = sexoSelecionadoDescricao,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = "Sexo") },
                        shape = RoundedCornerShape(26.dp),
                        leadingIcon = {
                            Icon(Icons.Filled.Face, contentDescription = "", tint = Color(0xFF2954C7))
                        },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .menuAnchor() // Usado para alinhar com o menu
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        listaSexos.forEach { sexo ->
                            DropdownMenuItem(
                                text = { Text(sexo.descricao) },
                                onClick = {
                                    sexoSelecionadoDescricao = sexo.descricao
                                    sexoSelecionadoState = sexo.id_sexo
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    value = senhaState,
                    onValueChange = { senhaState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Senha") },
                    shape = RoundedCornerShape(26.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = confirmarSenhaState,
                    onValueChange = { confirmarSenhaState = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Confirmar Senha") },
                    shape = RoundedCornerShape(26.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Lock, contentDescription = "", tint = Color(0xFF2954C7))
                    }
                )

                // Botão Cadastrar
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (validarCampos()) salvarUsuario()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF77B8FF), Color(0xFF0133D6))
                            ),
                            shape = RoundedCornerShape(30.dp) // Define o formato do botão
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent // Para garantir que o gradiente seja visível
                    ),
                    contentPadding = PaddingValues() // Remove o padding padrão para o gradiente preencher todo o botão
                )
                 {
                    Text(
                        text = "Cadastrar",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(text = "Já possui Conta?")
                    Text(
                        text = "Clique Aqui",
                        color = Color(0xFF0436D7),
                        modifier = Modifier
                            .clickable { controleDeNavegacao.navigate("telalogin") }
                    )
                }


            }

        }
    }
}
class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = formatoData(text.text)
        return TransformedText(AnnotatedString(formattedText), OffsetMapping.Identity)
    }
}

fun formatoData(text: String): String {
    val formatted = StringBuilder()
    for (i in text.indices) {
        formatted.append(text[i])
        if (i == 1 || i == 3) formatted.append("/")
    }
    return formatted.toString()

}



