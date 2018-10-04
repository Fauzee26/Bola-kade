package fauzi.hilmy.bola_kade

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import fauzi.hilmy.bola_kade.R.id.nav_next
import fauzi.hilmy.bola_kade.R.id.nav_prev
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
            }
            true
        }
        bottom_nav.selectedItemId = nav_prev
    }

    private fun loadsNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragNext(), FragNext::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadsPrevFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.framee, FragPrev(), FragPrev::class.java.simpleName)
                    .commit()
        }
    }
}
