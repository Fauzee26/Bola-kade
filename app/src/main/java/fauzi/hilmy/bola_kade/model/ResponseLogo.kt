package fauzi.hilmy.bola_kade.model

import com.google.gson.annotations.SerializedName

data class ResponseLogo(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>
)