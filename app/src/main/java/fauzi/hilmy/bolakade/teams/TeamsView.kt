package fauzi.hilmy.bolakade.teams

import fauzi.hilmy.bolakade.model.team.TeamsItem

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamsItem>)
}