package fauzi.hilmy.bolakade.model.match

import com.google.gson.annotations.SerializedName

data class ResponseSearch(
        @field:SerializedName("event")
        val events: List<DataLastNext>
)