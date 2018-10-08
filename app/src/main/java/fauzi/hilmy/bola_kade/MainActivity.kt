package fauzi.hilmy.bola_kade

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fauzi.hilmy.bola_kade.R.id.*
import fauzi.hilmy.bola_kade.fragment.FragFavorite
import fauzi.hilmy.bola_kade.fragment.FragNext
import fauzi.hilmy.bola_kade.fragment.FragPrev
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
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragNext(), FragNext::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadsPrevFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragPrev(), FragPrev::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadsFavFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragFavorite(), FragFavorite::class.java.simpleName)
                    .commit()
        }
    }
}
