package fauzi.hilmy.bolakade.model.logo

import com.google.gson.annotations.SerializedName

data class ResponseLogo(

	@field:SerializedName("teams")
	val teams: List<TeamsItem>
)