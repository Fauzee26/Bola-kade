package fauzi.hilmy.bola_kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fauzi.hilmy.bola_kade.R

/**
 * A simple [Fragment] subclass.
 *
 */
class FragMatch : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_frag_match, container, false)

    }
}