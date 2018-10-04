package fauzi.hilmy.bola_kade.model

import com.google.gson.annotations.SerializedName

data class ResponseLastNext(

	@field:SerializedName("events")
	val events: List<DataLastNext?>? = null
)