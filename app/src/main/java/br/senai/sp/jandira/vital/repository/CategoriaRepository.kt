package br.senai.sp.jandira.vital.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.senai.sp.jandira.vital.R
import br.senai.sp.jandira.vital.model.Categoria

// Criando uma classe para as categorias

class CategoriaRepository {
    @Composable
    fun mostrarTodasAsCategorias(): List<Categoria> {

        // Informacoes das categorias

        // Categoria Telemedicina
        val telemedicina = Categoria()
        telemedicina.id = 1
        telemedicina.titulo = "Telemedicina"
        telemedicina.selecionado = true           // Quando o usuario selecionar o card mudar de cor
        telemedicina.imagem = painterResource(id = R.drawable.telemedicina)


        // Categoria Médicos
        val medicos = Categoria()
        medicos.id = 2
        medicos.titulo = "Médicos"
        medicos.selecionado = false
        medicos.imagem = painterResource(id = R.drawable.medicos)


        // Categoria Galeria
        val galeria = Categoria()
        galeria.id = 3
        galeria.titulo = "Galeria"
        galeria.selecionado = false
        galeria.imagem = painterResource(id = R.drawable.galeria)


        // Categoria Consultas
        val consultas = Categoria()
        consultas.id = 4
        consultas.titulo = "Consultas"
        consultas.selecionado = false
        consultas.imagem = painterResource(id = R.drawable.consulta)


        // Categoria ChatBot
        val chatbot = Categoria()
        chatbot.id = 5
        chatbot.titulo = "ChatBot"
        chatbot.selecionado = false
        chatbot.imagem = painterResource(id = R.drawable.chatbot)


        // Categoria Suporte
        val suporte = Categoria()
        suporte.id = 6
        suporte.titulo = "Suporte"
        suporte.selecionado = false
        suporte.imagem = painterResource(id = R.drawable.suporte)




        return listOf(telemedicina, medicos, galeria, consultas, chatbot, suporte)

    }

}





