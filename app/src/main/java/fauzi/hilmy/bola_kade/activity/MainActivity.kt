package fauzi.hilmy.bola_kade.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fauzi.hilmy.bola_kade.R
import fauzi.hilmy.bola_kade.R.id.*
import fauzi.hilmy.bola_kade.favorite.FragFavorite
import fauzi.hilmy.bola_kade.matches.next.FragNext
import fauzi.hilmy.bola_kade.matches.prev.FragPrev
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                nav_prev -> {
                    loadsPrevFragment(savedInstanceState)
                }
                nav_next -> {
                    loadsNextFragment(savedInstanceState)
                }
                nav_fav -> {
                    loadsFavFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_nav.selectedItemId = nav_prev
    }

    private fun loadsNextFragment(savedInstanceState: Bundle?) {
        title = getString(R.string.next_match)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragNext(), FragNext::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadsPrevFragment(savedInstanceState: Bundle?) {
        title = getString(R.string.prev_match)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragPrev(), FragPrev::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadsFavFragment(savedInstanceState: Bundle?) {
        title = getString(R.string.favorite)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragFavorite(), FragFavorite::class.java.simpleName)
                    .commit()
        }
    }
}