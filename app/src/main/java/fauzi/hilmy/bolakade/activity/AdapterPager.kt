package fauzi.hilmy.bolakade.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class AdapterPager(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val frag_list: MutableList<Fragment> = mutableListOf()
    private val frag_title: MutableList<String> = mutableListOf()

    override fun getItem(p0: Int) = frag_list.get(p0)

    override fun getPageTitle(position: Int) = frag_title.get(position)

    override fun getCount() = frag_list.size

    fun addTabFragment(fragment: Fragment, title: String){
        frag_list.add(fragment)
        frag_title.add(title)
    }
}