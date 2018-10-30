package fauzi.hilmy.bolakade.matches.next

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bolakade.api.ApiRepository
import com.google.gson.Gson
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.activity.DetailActivity
import fauzi.hilmy.bolakade.matches.AdapterLastNext
import fauzi.hilmy.bolakade.model.match.DataLastNext
import fauzi.hilmy.bolakade.util.MyConstant
import fauzi.hilmy.bolakade.util.MyConstant.ID_LIGA
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.fragment_frag_next.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class FragNext : Fragment(), NextMatchView {
    private var nextData: MutableList<DataLastNext> = mutableListOf()
    private lateinit var nextAdapter: AdapterLastNext
    private lateinit var presenter: NextMatchPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()

        presenter = NextMatchPresenter(this, request, gson)

        swipeNext.setOnRefreshListener {
            //            getNext()
            presenter.getNextList(ID_LIGA)
        }

        nextAdapter = AdapterLastNext(nextData) {
            startActivity<DetailActivity>(
                    MyConstant.ID_EVENT to "${it.idEvent}"
            )
        }
        recyclerNext.layoutManager = LinearLayoutManager(context)
        recyclerNext.adapter = nextAdapter
        presenter.getNextList(ID_LIGA)
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
