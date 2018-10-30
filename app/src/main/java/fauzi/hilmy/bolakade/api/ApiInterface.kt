package fauzi.hilmy.bolakade.api

import fauzi.hilmy.bolakade.model.logo.ResponseLogo
import fauzi.hilmy.bolakade.model.match.ResponsePrevNext
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/{key}/lookupteam.php")
    fun getDetailTeam(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponseLogo>

    @GET("api/v1/json/{key}/lookupevent.php")

    fun getDetail(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponsePrevNext>
}