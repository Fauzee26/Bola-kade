package fauzi.hilmy.bolakade.teams

import android.util.Log
import com.google.gson.Gson
import fauzi.hilmy.bolakade.api.ApiRepository
import fauzi.hilmy.bolakade.api.TheSportDBApi
import fauzi.hilmy.bolakade.model.team.ResponseTeam
import fauzi.hilmy.bolakade.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getAllTeam(league: String?, isSearch: Boolean) {
        view.showLoading()

        val requested = if (isSearch) TheSportDBApi.getTeamSearch(league) else TheSportDBApi.getTeams(league)

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(requested),
                        ResponseTeam::class.java
                )
            }
            Log.d("View Loading", data.toString())
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }
}