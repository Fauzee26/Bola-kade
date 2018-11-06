package fauzi.hilmy.bolakade.detail.player

import android.util.Log
import com.google.gson.Gson
import fauzi.hilmy.bolakade.api.ApiRepository
import fauzi.hilmy.bolakade.api.TheSportDBApi
import fauzi.hilmy.bolakade.model.player.ResponsePlayer
import fauzi.hilmy.bolakade.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayerPresenter(
        private val view: DetailPlayerView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerDetail(id: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getDetailPlayer(id)),
                        ResponsePlayer::class.java
                )
            }
            view.showPlayerDetail(data.await().player)
            Log.wtf("Detail Presenter Player", data.await().player.toString())
            view.hideLoading()
        }
    }

}
