package fauzi.hilmy.bola_kade.matches.prev

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import fauzi.hilmy.bola_kade.activity.DetailActivity
import fauzi.hilmy.bola_kade.R
import fauzi.hilmy.bola_kade.matches.AdapterLastNext
import fauzi.hilmy.bola_kade.api.ApiRepository
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.util.MyConstant.ID_EVENT
import fauzi.hilmy.bola_kade.util.MyConstant.ID_LIGA
import fauzi.hilmy.bola_kade.util.invisible
import fauzi.hilmy.bola_kade.util.visible
import kotlinx.android.synthetic.main.fragment_frag_prev.*
import org.jetbrains.anko.support.v4.startActivity

class FragPrev : Fragment(), PrevMatchView {

    private var dataa: MutableList<DataLastNext> = mutableListOf()
    private lateinit var last: AdapterLastNext
    private lateinit var presenter: PrevMatchPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()

        presenter = PrevMatchPresenter(this, request, gson)

        swipeLast.setOnRefreshListener {
            //            getNext()
            presenter.getPrevList(ID_LIGA)
        }

        last = AdapterLastNext(dataa) {
            startActivity<DetailActivity>(
                    ID_EVENT to "${it.idEvent}"
            )
        }
        recyclerPrev.layoutManager = LinearLayoutManager(context)
        recyclerPrev.adapter = last
        presenter.getPrevList(ID_LIGA)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_prev, container, false)
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