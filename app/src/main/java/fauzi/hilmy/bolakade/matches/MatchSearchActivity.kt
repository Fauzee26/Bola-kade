package fauzi.hilmy.bolakade.matches

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.widget.Toast
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.api.ApiClient
import fauzi.hilmy.bolakade.detail.DetailMatchActivity
import fauzi.hilmy.bolakade.model.match.DataLastNext
import fauzi.hilmy.bolakade.model.match.ResponsePrevNext
import fauzi.hilmy.bolakade.model.match.ResponseSearch
import fauzi.hilmy.bolakade.util.MyConstant
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.activity_match_search.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Response

class MatchSearchActivity : AppCompatActivity() {
    private var data: MutableList<DataLastNext> = mutableListOf()

    private lateinit var lastNext: AdapterLastNext
    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.isIconified = false
        searchView.setIconifiedByDefault(false)
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (text?.length!! > 2) {
                    query = text
                    getTeam(query)
                }
                return false
            }

        })
        return true
    }

    private fun getTeam(query: String) {
        progresSearchMatch.visible()
        val callLeague = ApiClient().getInstance().getSearchMatch(query)
        Log.d("Request URl", callLeague.toString())
        callLeague.enqueue(object : retrofit2.Callback<ResponseSearch> {
            override fun onResponse(call: Call<ResponseSearch>, response: Response<ResponseSearch>) {
                Log.e("Response", response.body().toString())
                if (response.isSuccessful) {
                    val dataLastNext = response.body()?.events
                    dataLastNext?.forEach { it ->
                        if (it.strSport.equals("Soccer")) {
                            progresSearchMatch.invisible()
                            data.add(it)
                            Log.v("Data Searched", it.idEvent)
                            lastNext = AdapterLastNext(data, true) {
                                startActivity<DetailMatchActivity>(
                                        MyConstant.ID_EVENT to "${it.idEvent}"
                                )
                            }
                            lastNext.notifyDataSetChanged()
                            recycleSearchMatch.adapter = lastNext
                            recycleSearchMatch.layoutManager = LinearLayoutManager(applicationContext)

                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                Log.e("Error: ", t.message)
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                progresSearchMatch.invisible()

            }
        })
    }

}
