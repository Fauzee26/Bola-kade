package fauzi.hilmy.bola_kade.model

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<DataLastNext>)
}