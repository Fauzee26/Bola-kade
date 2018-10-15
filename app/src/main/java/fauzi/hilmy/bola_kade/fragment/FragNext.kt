package fauzi.hilmy.bola_kade.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bola_kade.api.ApiRepository
import com.google.gson.Gson
import fauzi.hilmy.bola_kade.R
import fauzi.hilmy.bola_kade.activity.DetailActivity
import fauzi.hilmy.bola_kade.adapter.AdapterLastNext
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.model.MatchPresenter
import fauzi.hilmy.bola_kade.model.MatchView
import fauzi.hilmy.bola_kade.util.MyConstant
import fauzi.hilmy.bola_kade.util.MyConstant.ID_LIGA
import fauzi.hilmy.bola_kade.util.invisible
import fauzi.hilmy.bola_kade.util.visible
import kotlinx.android.synthetic.main.fragment_frag_next.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class FragNext : Fragment(), MatchView {
    private var nextData: MutableList<DataLastNext> = mutableListOf()
    private lateinit var nextAdapter: AdapterLastNext
    private lateinit var presenter: MatchPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()

        presenter = MatchPresenter(this, request, gson)

        swipeNext.setOnRefreshListener {
            //            getNext()
            presenter.getTeamList(ID_LIGA)
        }

        nextAdapter = AdapterLastNext(nextData) {
            startActivity<DetailActivity>(
                    MyConstant.ID_EVENT to "${it.idEvent}"
            )
        }
        recyclerNext.layoutManager = LinearLayoutManager(context)
        recyclerNext.adapter = nextAdapter
        presenter.getTeamList(ID_LIGA)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_frag_next, container, false)
    }

    override fun showLoading() {
        progresNext.visible()
    }

    override fun hideLoading() {
        progresNext.invisible()
    }

    override fun showTeamList(data: List<DataLastNext>) {
        swipeNext.isRefreshing = false
        nextData.clear()
        nextData.addAll(data)
        nextAdapter.notifyDataSetChanged()
    }
}
