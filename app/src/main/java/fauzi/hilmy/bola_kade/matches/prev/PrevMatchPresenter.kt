package fauzi.hilmy.bola_kade.matches.prev

import fauzi.hilmy.bola_kade.api.ApiRepository
import fauzi.hilmy.bola_kade.util.CoroutineContextProvider
import com.google.gson.Gson
import fauzi.hilmy.bola_kade.api.TheSportDBApi
import fauzi.hilmy.bola_kade.model.ResponsePrevNext
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPrevList(league: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPrev(league)),
                        ResponsePrevNext::class.java
                )
            }
            view.showTeamList(data.await().events)
            view.hideLoading()
        }
    }
}