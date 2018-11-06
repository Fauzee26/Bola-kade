package fauzi.hilmy.bolakade.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import com.google.gson.Gson

import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.api.ApiRepository
import fauzi.hilmy.bolakade.detail.team.DetailTeamActivity
import fauzi.hilmy.bolakade.model.team.TeamsItem
import fauzi.hilmy.bolakade.util.MyConstant
import fauzi.hilmy.bolakade.util.invisible
import fauzi.hilmy.bolakade.util.visible
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamsFragment : Fragment(), TeamsView {
    private var dataTeam: MutableList<TeamsItem> = mutableListOf()
    private lateinit var last: TeamsAdapter
    private lateinit var presenter: TeamsPresenter
    private var idLeague = "English Premier League"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arrayLeague = resources.getStringArray(R.array.leagueNamearray)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        val spinAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, arrayLeague)
        spinTeam.adapter = spinAdapter
        spinTeam.onItemSelectedListener {
            onItemSelected { adapterView, view, i, l ->
                val selectedleague = spinTeam.selectedItem
                idLeague = selectedleague.toString()

                Log.v("Selected League", idLeague)

                if (idLeague.isNotEmpty()) {
                    presenter.getAllTeam(idLeague, false)
                }
            }
        }

        last = TeamsAdapter(dataTeam) {
            startActivity<DetailTeamActivity>(
                    MyConstant.ID_EVENT to it
            )
        }

        recyclerTeam.layoutManager = LinearLayoutManager(context)
        recyclerTeam.adapter = last
        presenter.getAllTeam(idLeague, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (text?.length!! > 2) {
                    presenter.getAllTeam(text, true)
                }
                return false
            }

        })
    }

    override fun showLoading() {
        progressTeam.visible()
    }

    override fun hideLoading() {
        progressTeam.invisible()
    }

    override fun showTeamList(data: List<TeamsItem>) {
        Log.wtf("List Team", data.toString())

        dataTeam.clear()
        dataTeam.addAll(data)
        last.notifyDataSetChanged()
    }
}
