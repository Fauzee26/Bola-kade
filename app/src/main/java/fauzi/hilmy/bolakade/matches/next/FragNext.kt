package fauzi.hilmy.bolakade.matches.next

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
import fauzi.hilmy.bolakade.util.MyConstant
import fauzi.hilmy.bolakade.util.Util.Companion.getListLeague
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.fragment_match_list.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class FragNext : Fragment(), NextMatchView {

    private var nextData: MutableList<DataLastNext> = mutableListOf()
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var nextAdapter: AdapterLastNext
    private lateinit var presenter: NextMatchPresenter
    private var idLeague = "4335"
    private lateinit var spinAdaptera: SpinAdaptera

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()

        presenter = NextMatchPresenter(this, request, gson)

        getListLeague(requireContext(), leagues)
        spinAdaptera = SpinAdaptera(requireContext(), android.R.layout.simple_spinner_dropdown_item, getListLeague(requireContext(), leagues))

        Log.e("LIST LEAGUE NEXT", leagues.toString())

        spinLeague.adapter = spinAdaptera
        spinLeague.setSelection(spinAdaptera.getPosition(League("4335", "Spanish La Liga")))
        spinLeague.onItemSelectedListener {
            onItemSelected { adapterView, view, i, l ->
                val league = spinLeague.selectedItem as? League

                idLeague = league?.leagueId.toString()
                if (idLeague.isNotEmpty()) {
                    presenter.getNextList(idLeague)
                }
            }

        }
        swipeLast.setOnRefreshListener {
            //            getNext()
            presenter.getNextList(idLeague)
        }

        nextAdapter = AdapterLastNext(nextData, false) {
            startActivity<DetailMatchActivity>(
                    MyConstant.ID_EVENT to "${it.idEvent}"
            )
        }
        recyclerPrev.layoutManager = LinearLayoutManager(context)
        recyclerPrev.adapter = nextAdapter
        presenter.getNextList(idLeague)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

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
        nextData.clear()
        nextData.addAll(data)
        nextAdapter.notifyDataSetChanged()
    }
}
