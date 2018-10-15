package fauzi.hilmy.bola_kade.model

import fauzi.hilmy.bola_kade.api.ApiRepository
import fauzi.hilmy.bola_kade.util.CoroutineContextProvider
import com.google.gson.Gson
import fauzi.hilmy.bola_kade.api.TheSportDBApi
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNext(league)),
                        ResponseNext::class.java
                )
            }
            view.showTeamList(data.await().events)
            view.hideLoading()
        }
    }
}