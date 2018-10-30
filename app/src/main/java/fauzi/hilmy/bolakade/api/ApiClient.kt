package fauzi.hilmy.bolakade.api

import fauzi.hilmy.bolakade.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private fun setInit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInstance(): ApiInterface {
        return setInit().create(ApiInterface::class.java)
    }
}