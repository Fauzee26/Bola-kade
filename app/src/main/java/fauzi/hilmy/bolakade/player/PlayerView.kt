package fauzi.hilmy.bolakade.player

import fauzi.hilmy.bolakade.model.player.PlayerItem

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<PlayerItem>)
}