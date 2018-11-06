package fauzi.hilmy.bolakade.player


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.api.ApiRepository
import fauzi.hilmy.bolakade.detail.player.DetailPlayerActivity
import fauzi.hilmy.bolakade.model.player.PlayerItem
import fauzi.hilmy.bolakade.util.MyConstant
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.fragment_player.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class PlayerFragment : Fragment(), PlayerView {

    private var playerData: MutableList<PlayerItem> = mutableListOf()
    private lateinit var adapter: PlayerAdapter
    private lateinit var presenter: PlayerPresenter
    private lateinit var idLeague: String

    companion object {
        fun fragmentChild(id: String?): PlayerFragment {
            val fragment = PlayerFragment()
            fragment.idLeague = id!!

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PlayerAdapter(playerData) {
            startActivity<DetailPlayerActivity>(
                    "idPlayer" to it
            )
            Log.e("Player Clicked", it.playerId)
        }

        val request = ApiRepository()
        val gson = Gson()

        presenter = PlayerPresenter(this, request, gson)

        recyclerPlayer.layoutManager = LinearLayoutManager(context)
        recyclerPlayer.adapter = adapter
        presenter.getAllPlayer(idLeague)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)

    }

    override fun showLoading() {
        progressPlayer.invisible()
    }

    override fun hideLoading() {
        progressPlayer.invisible()
    }

    override fun showPlayerList(data: List<PlayerItem>) {
        playerData.clear()
        playerData.addAll(data)
        adapter.notifyDataSetChanged()
    }

}