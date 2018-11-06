package fauzi.hilmy.bolakade.detail.player

import fauzi.hilmy.bolakade.model.player.PlayerItem

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<PlayerItem>)
}