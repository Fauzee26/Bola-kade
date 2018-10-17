package fauzi.hilmy.bola_kade.matches.next

import fauzi.hilmy.bola_kade.model.DataLastNext

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DataLastNext>)
}