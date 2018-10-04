package fauzi.hilmy.bola_kade

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fauzi.hilmy.bola_kade.adapter.AdapterLast
import fauzi.hilmy.bola_kade.api.ApiClient
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.model.ResponseLastNext
import fauzi.hilmy.bola_kade.util.MyConstant
import fauzi.hilmy.bola_kade.util.MyConstant.Companion.API_KEY
import fauzi.hilmy.bola_kade.util.MyConstant.Companion.ID_LIGA
import kotlinx.android.synthetic.main.fragment_frag_next.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class FragNext : Fragment() {

    private var nextData: MutableList<DataLastNext> = mutableListOf()
    private lateinit var nextAdapter: AdapterLast

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeNext.setOnRefreshListener {
            getNext()
        }
        nextAdapter = AdapterLast(nextData) {
            startActivity<DetailActivity>(
                    MyConstant.ID_EVENT to "${it.idEvent}"
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        getNext()

        return inflater.inflate(R.layout.fragment_frag_next, container, false)
    }

    private fun getNext() {
        val callLeague = ApiClient().getInstance().getNext15(API_KEY, ID_LIGA)
        callLeague.enqueue(object : retrofit2.Callback<ResponseLastNext> {
            override fun onResponse(call: Call<ResponseLastNext>, response: Response<ResponseLastNext>) {
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    recyclerNext.layoutManager = LinearLayoutManager(context)
                    nextData.clear()
                    nextData.addAll(dataLastNext as List<DataLastNext>)
                    nextAdapter.notifyDataSetChanged()
                    recyclerNext.adapter = nextAdapter
                    swipeNext.isRefreshing = false
                    progresNext.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseLastNext>, t: Throwable) {
                Log.e("Error: Load Last = ", t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                progresNext.visibility = View.GONE
                swipeNext.isRefreshing = false

            }
        })
    }
}
