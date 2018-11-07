package fauzi.hilmy.bolakade.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.R.id.*
import fauzi.hilmy.bolakade.favorite.FragmentFavorite
import fauzi.hilmy.bolakade.matches.MatchFragment
import fauzi.hilmy.bolakade.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setContentView(R.layout.activity_main)

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                nav_match -> {
                    supportActionBar?.show()
                    title = "Matches"
                    addFragmentOnTop(MatchFragment())
                }
                nav_team -> {
                    supportActionBar?.show()
                    title = "Teams"
                    addFragmentOnTop(TeamsFragment())
                }
                nav_fav -> {
                    supportActionBar?.show()
                    title = "Favorite"
                    addFragmentOnTop(FragmentFavorite())
                }
            }
            true
        }
        bottom_nav.selectedItemId = nav_match
    }

    private fun addFragmentOnTop(fragment: Fragment) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, fragment, fragment::class.java.simpleName)
                    .commit()
        }
    }
}