package fauzi.hilmy.bolakade.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.activity.AdapterPager
import fauzi.hilmy.bolakade.favorite.Favmatch.FragmentFavoriteMatch
import fauzi.hilmy.bolakade.favorite.Favteam.FragmentFavoriteTeam
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentFavorite : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager(favViewpager)

        favTab.setupWithViewPager(favViewpager)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = AdapterPager(childFragmentManager)
        adapter.addTabFragment(FragmentFavoriteMatch(), "MATCH")
        adapter.addTabFragment(FragmentFavoriteTeam(), "TEAM")
        viewPager.adapter = adapter
    }

}
