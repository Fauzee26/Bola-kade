package fauzi.hilmy.bolakade.matches.next

import fauzi.hilmy.bolakade.model.match.DataLastNext

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DataLastNext>)
}