package fauzi.hilmy.bolakade.matches


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*

import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.R.id.action_searchh
import fauzi.hilmy.bolakade.activity.AdapterPager
import fauzi.hilmy.bolakade.matches.next.FragNext
import fauzi.hilmy.bolakade.matches.prev.FragPrev
import fauzi.hilmy.bolakade.model.League
import kotlinx.android.synthetic.main.fragment_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager(match_viewpager)

        match_tab.setupWithViewPager(match_viewpager)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.nav_search, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            action_searchh -> {
                val intent = Intent(requireContext(), MatchSearchActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = AdapterPager(childFragmentManager)
        adapter.addTabFragment(FragPrev(), "LAST")
        adapter.addTabFragment(FragNext(), "NEXT")
        viewPager.adapter = adapter
    }

}
