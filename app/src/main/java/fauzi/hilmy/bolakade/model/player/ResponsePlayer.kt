package fauzi.hilmy.bolakade.model.player

import com.google.gson.annotations.SerializedName

data class ResponsePlayer(

        @field:SerializedName("player")
        val player: List<PlayerItem>
)