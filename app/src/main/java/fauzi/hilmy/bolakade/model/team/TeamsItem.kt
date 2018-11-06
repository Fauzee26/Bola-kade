package fauzi.hilmy.bolakade.model.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsItem(

        @field:SerializedName("idTeam")
        val teamId: String? = null,

        @field:SerializedName("strTeam")
        val teamName: String? = null,

        @field:SerializedName("intFormedYear")
        val teamYear: String? = null,

        @field:SerializedName("strManager")
        val teamManager: String? = null,

        @field:SerializedName("strStadium")
        val teamStadium: String? = null,

        @field:SerializedName("strDescriptionEN")
        val teamDescription: String? = null,

        @field:SerializedName("strTeamBadge")
        val teamLogo: String? = null

) : Parcelable