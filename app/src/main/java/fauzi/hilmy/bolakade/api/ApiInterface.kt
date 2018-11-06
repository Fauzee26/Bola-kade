package fauzi.hilmy.bolakade.api

import fauzi.hilmy.bolakade.model.logo.ResponseLogo
import fauzi.hilmy.bolakade.model.match.ResponsePrevNext
import fauzi.hilmy.bolakade.model.match.ResponseSearch
import fauzi.hilmy.bolakade.model.player.ResponsePlayer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(
            @Query("id") parameter: String?
    ): Call<ResponseLogo>

    @GET("api/v1/json/{key}/lookupevent.php")
    fun getDetail(
            @Path("key") path: String?,
            @Query("id") parameter: String?
    ): Call<ResponsePrevNext>

    @GET("api/v1/json/1/searchevents.php")
    fun getSearchMatch(
            @Query("e") query: String?
    ): Call<ResponseSearch>

    @GET("api/v1/json/1/lookupplayer.php")
    fun getDetailPlayer(
            @Query("id") query: String?
    ): Call<ResponsePlayer>

}