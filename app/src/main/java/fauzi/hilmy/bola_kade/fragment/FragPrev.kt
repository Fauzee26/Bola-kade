package fauzi.hilmy.bola_kade.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fauzi.hilmy.bola_kade.DetailActivity
import fauzi.hilmy.bola_kade.R
import fauzi.hilmy.bola_kade.adapter.AdapterLastNext
import fauzi.hilmy.bola_kade.api.ApiClient
import fauzi.hilmy.bola_kade.model.DataLastNext
import fauzi.hilmy.bola_kade.model.ResponseLastNext
import fauzi.hilmy.bola_kade.util.MyConstant.Companion.API_KEY
import fauzi.hilmy.bola_kade.util.MyConstant.Companion.ID_EVENT
import fauzi.hilmy.bola_kade.util.MyConstant.Companion.ID_LIGA
import kotlinx.android.synthetic.main.fragment_frag_prev.*
import org.jetbrains.anko.support.v4.startActivity
import retrofit2.Call
import retrofit2.Response

class FragPrev : Fragment() {

    private var data: MutableList<DataLastNext> = mutableListOf()
    private lateinit var last: AdapterLastNext

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        getlast15()
        last = AdapterLastNext(data) {
            startActivity<DetailActivity>(
                    ID_EVENT to "${it.idEvent}"
            )
        }
        return inflater.inflate(R.layout.fragment_frag_prev, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeLast.setOnRefreshListener {
            getlast15()
        }
    }

     fun getlast15() {
        val callLeague = ApiClient().getInstance().getLast15(API_KEY, ID_LIGA)
        callLeague.enqueue(object : retrofit2.Callback<ResponseLastNext> {
            override fun onResponse(call: Call<ResponseLastNext>, response: Response<ResponseLastNext>) {
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    recyclerPrev.layoutManager = LinearLayoutManager(context)
                    data.clear()
                    data.addAll(dataLastNext as List<DataLastNext>)
                    last.notifyDataSetChanged()
                    recyclerPrev.adapter = last
                    swipeLast.isRefreshing = false
                    progresLast.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseLastNext>, t: Throwable) {
                Log.e("Error: Load Last = ", t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                progresLast.visibility = View.GONE
                swipeLast.isRefreshing = false

            }
        })
    }

}