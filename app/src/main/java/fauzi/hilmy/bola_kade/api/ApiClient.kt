package fauzi.hilmy.bola_kade.api

import fauzi.hilmy.bola_kade.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    fun setInit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInstance(): ApiInterface {
        return setInit().create(ApiInterface::class.java)
    }
}