package fauzi.hilmy.bolakade.player

import com.google.gson.Gson
import fauzi.hilmy.bolakade.api.ApiRepository
import fauzi.hilmy.bolakade.api.TheSportDBApi
import fauzi.hilmy.bolakade.model.player.ResponsePlayer
import fauzi.hilmy.bolakade.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getAllPlayer(id: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getAllPlayer(id)),
                        ResponsePlayer::class.java
                )
            }
            view.showPlayerList(data.await().player)
            view.hideLoading()
        }
    }
}