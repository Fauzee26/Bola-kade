package fauzi.hilmy.bola_kade.api

import fauzi.hilmy.bola_kade.model.ResponseLastNext
import fauzi.hilmy.bola_kade.model.ResponseLogo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/{key}/eventspastleague.php")
    fun getLast15(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLastNext>

    @GET("api/v1/json/{key}/eventsnextleague.php")
    fun getNext15(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLastNext>

    @GET("api/v1/json/{key}/lookupteam.php")
    fun getDetailTeam(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLogo>

    @GET("api/v1/json/{key}/lookupevent.php")

    fun getDetail(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLastNext>
}