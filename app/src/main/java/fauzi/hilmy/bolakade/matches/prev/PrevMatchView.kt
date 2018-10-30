package fauzi.hilmy.bolakade.matches.prev

import fauzi.hilmy.bolakade.model.match.DataLastNext

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DataLastNext>)
}