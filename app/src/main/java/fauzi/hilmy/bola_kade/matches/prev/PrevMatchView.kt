package fauzi.hilmy.bola_kade.matches.prev

import fauzi.hilmy.bola_kade.model.DataLastNext

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DataLastNext>)
}