package fauzi.hilmy.bolakade.favorite.Favteam


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.R.color.colorAccent
import fauzi.hilmy.bolakade.detail.team.DetailTeamActivity
import fauzi.hilmy.bolakade.favorite.db.FavTeam
import fauzi.hilmy.bolakade.favorite.db.database
import fauzi.hilmy.bolakade.model.team.TeamsItem
import fauzi.hilmy.bolakade.util.MyConstant.ID_EVENT
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentFavoriteTeam : Fragment(), AnkoComponent<Context> {

    private var favTeam: MutableList<FavTeam> = mutableListOf()
    private lateinit var adapter: AdapterFavoriteTeam
    private lateinit var recycler: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = AdapterFavoriteTeam(favTeam) {

            val teams = TeamsItem(
                    it.teamId, it.teamName, it.teamYear, it.teamManager, it.teamStadium, it.teamDescription, it.teamLogo
            )
            startActivity<DetailTeamActivity>(
                    ID_EVENT to teams
            )
        }

        recycler.adapter = adapter
        showData()
        swipeRefresh.onRefresh {
            favTeam.clear()
            showData()
        }
    }

    private fun showData() {
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(FavTeam.TABLE_TEAM)
            val favTeamm = result.parseList(classParser<FavTeam>())
            favTeam.addAll(favTeamm)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            topPadding = dip(16)
            rightPadding = dip(16)
            leftPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipedd
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                recycler = recyclerView {
                    id = R.id.recycler_fav
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}
