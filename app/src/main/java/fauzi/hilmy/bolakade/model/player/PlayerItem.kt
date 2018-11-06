package fauzi.hilmy.bolakade.model.player

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerItem(

        @field:SerializedName("idPlayer")
        val playerId: String? = null,

        @field:SerializedName("strPlayer")
        val playerName: String? = null,

        @field:SerializedName("strDescriptionEN")
        val playerDescription: String? = null,

        @field:SerializedName("strPosition")
        val playerPosition: String? = null,

        @field:SerializedName("strHeight")
        val playerHeight: String? = null,

        @field:SerializedName("strWeight")
        val playerWeight: String? = null,

        @field:SerializedName("strCutout")
        val playerCutout: String? = null,

        @field:SerializedName("strFanart1")
        val playerFanart: String? = null
) : Parcelable