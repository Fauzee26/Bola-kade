package fauzi.hilmy.bolakade.matches.prev

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.gson.Gson
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.detail.DetailMatchActivity
import fauzi.hilmy.bolakade.api.ApiRepository
import fauzi.hilmy.bolakade.matches.AdapterLastNext
import fauzi.hilmy.bolakade.matches.SpinAdaptera
import fauzi.hilmy.bolakade.model.League
import fauzi.hilmy.bolakade.model.match.DataLastNext
import fauzi.hilmy.bolakade.util.MyConstant.ID_EVENT
import fauzi.hilmy.bolakade.util.Util.Companion.getListLeague
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.fragment_match_list.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.startActivity

class FragPrev : Fragment(), PrevMatchView {

    private var dataa: MutableList<DataLastNext> = mutableListOf()
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var last: AdapterLastNext
    private lateinit var presenter: PrevMatchPresenter
    private var idLeague = "4335"
    private lateinit var spinAdaptera: SpinAdaptera

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()

        presenter = PrevMatchPresenter(this, request, gson)

        getListLeague(requireContext(), leagues)
        spinAdaptera = SpinAdaptera(requireContext(), android.R.layout.simple_spinner_dropdown_item, getListLeague(requireContext(), leagues))

        Log.e("LIST LEAGUE PREV", leagues.toString())

        spinLeague.adapter = spinAdaptera
        spinLeague.setSelection(spinAdaptera.getPosition(League("4335", "Spanish La Liga")))
        spinLeague.onItemSelectedListener {
            onItemSelected { adapterView, view, i, l ->
                val selectedleague = spinLeague.selectedItem as League
                idLeague = selectedleague.leagueId.toString()

//                idLeague = selectedleague.toString()
                if (idLeague.isNotEmpty()) {
                    presenter.getPrevList(idLeague)
                }
            }
        }
        swipeLast.setOnRefreshListener {
            //            getNext()
            presenter.getPrevList(idLeague)
        }

        last = AdapterLastNext(dataa, true) {
            startActivity<DetailMatchActivity>(
                    ID_EVENT to "${it.idEvent}"
            )
        }
        recyclerPrev.layoutManager = LinearLayoutManager(context)
        recyclerPrev.adapter = last
        presenter.getPrevList(idLeague)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun showLoading() {
        progresLast.visible()
    }

    override fun hideLoading() {
        progresLast.invisible()
    }

    override fun showTeamList(data: List<DataLastNext>) {
        swipeLast.isRefreshing = false
        dataa.clear()
        dataa.addAll(data)
        last.notifyDataSetChanged()
    }
}