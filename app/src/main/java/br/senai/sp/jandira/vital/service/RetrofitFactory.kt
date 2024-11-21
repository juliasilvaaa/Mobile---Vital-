package br.senai.sp.jandira.vital.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    // Variavel privada para guardar a url base
    private val BASE_URL = "https://vital-back-geh2haera4f5hzfb.brazilsouth-01.azurewebsites.net/v1/vital/"


    // Arquivo para chamar a url base

    // Cliente retrofit - ele que faz as requisicoes
    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getUserService(): UserService {
        return retrofitFactory.create(UserService::class.java)

    }

    // Vai gerar o objeto que tem as requisicoes
    fun getEspecialidadeService(): EspecialidadeService{
        return retrofitFactory.create(EspecialidadeService::class.java)
    }

    // Medicos
    fun getMedicoService(): MedicoService{
        return  retrofitFactory.create(MedicoService::class.java)
    }


    // Consultas
    fun getConsultaService(): ConsultaService{
        return  retrofitFactory.create(ConsultaService::class.java)
    }

    fun getUsuarioService(): UserService {
 return retrofitFactory.create(UserService::class.java)
    }

    fun getSexoService(java: Class<SexoService>): SexoService {
        return retrofitFactory.create(SexoService::class.java)
    }

    fun getAvaliacaoService(): AvaliacaoService {
        return retrofitFactory.create(AvaliacaoService::class.java)
    }


}


