package fauzi.hilmy.bolakade.detail.team

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.R.drawable.add
import fauzi.hilmy.bolakade.R.drawable.added
import fauzi.hilmy.bolakade.R.id.addMenu
import fauzi.hilmy.bolakade.R.menu.fav
import fauzi.hilmy.bolakade.activity.AdapterPager
import fauzi.hilmy.bolakade.favorite.db.FavTeam
import fauzi.hilmy.bolakade.favorite.db.database
import fauzi.hilmy.bolakade.model.team.TeamsItem
import fauzi.hilmy.bolakade.player.PlayerFragment
import fauzi.hilmy.bolakade.util.MyConstant.ID_EVENT
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailTeamActivity : AppCompatActivity() {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var teams: TeamsItem
    private var dataa: MutableList<TeamsItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        teams = intent.getParcelableExtra(ID_EVENT)

        showData()
        favoriteState()
        setFavorite()

        setupViewPager(detail_viewpager)
        detail_tab.setupWithViewPager(detail_viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = AdapterPager(supportFragmentManager)
        adapter.addTabFragment(FragmentOverview.fragmentChild(teams.teamDescription), "Overview")
        adapter.addTabFragment(PlayerFragment.fragmentChild(teams.teamId), "Player")
        viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(fav, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            addMenu -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showData() {
        dataa.add(teams)
        Picasso.get().load(teams.teamLogo)
                .placeholder(R.drawable.no_image)
                .into(imgDetailTeam)
        txtNamaDetailTeam.text = teams.teamName
        txtTahunDetailTeam.text = teams.teamYear
        txtStadiumDetailTeam.text = teams.teamStadium
    }

    private fun favoriteState() {
        database.use {
            val result = select(FavTeam.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to teams.teamId!!.any())
            val favorite = result.parseList(classParser<FavTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(FavTeam.TABLE_TEAM,
                        FavTeam.TEAM_ID to teams.teamId,
                        FavTeam.TEAM_NAME to teams.teamName,
                        FavTeam.TEAM_LOGO to teams.teamLogo,
                        FavTeam.TEAM_YEAR to teams.teamYear,
                        FavTeam.TEAM_MANAGER to teams.teamManager,
                        FavTeam.TEAM_STADIUM to teams.teamStadium,
                        FavTeam.TEAM_DESCRIPTION to teams.teamDescription)
            }
            snackbar(linearMain, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(linearMain, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(FavTeam.TABLE_TEAM, "(TEAM_ID = {id})",
                        "id" to teams.teamId!!)
            }
            snackbar(linearMain, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(linearMain, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, added)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, add)
    }
}
