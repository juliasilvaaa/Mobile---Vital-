package br.senai.sp.jandira.vital.service


import br.senai.sp.jandira.vital.model.Login
import retrofit2.http.GET
import retrofit2.http.Query
import br.senai.sp.jandira.vital.model.Usuario
import br.senai.sp.jandira.vital.model.UsuarioLogin
import br.senai.sp.jandira.vital.model.UsuarioResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    // Salvar o Usuario
    @Headers("Content-Type: application/json")
    @POST("usuario")
    fun salvarUsuario(@Body usuario: Usuario): Call<Usuario>

    @Headers("Content-Type: application/json")
    @POST("loginUsuario")
    fun loginUsuario(@Body login: Login): Call<UsuarioLogin>

    // Buscar pelo Email
    @GET("usuario/email")
    fun buscarPeloEmail(@Query("email") email: String): Call<Usuario?>

    // Buscar pelo ID - result do usuario
    @GET("usuario/{id}")
    fun getUsuarioById(@Path("id") id: Int) : Call<UsuarioResponse>

}
