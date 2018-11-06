package fauzi.hilmy.bolakade.detail.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.model.team.TeamsItem
import kotlinx.android.synthetic.main.fragment_overview.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentOverview : Fragment() {

    private var overview : String? = null

    companion object {
        fun fragmentChild(overview: String?): FragmentOverview {
            val fragment = FragmentOverview()
            fragment.overview = overview

            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (overview != null){
            txtOverview.text = overview
        } else{
            txtOverview.text = getString(R.string.overview_null)
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }


}
