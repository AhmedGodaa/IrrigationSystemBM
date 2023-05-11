package banquemisr.com.sensiorservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Land(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("name") val name: String? = null,
    @JsonProperty("area") var area: Double? = null,
    @JsonProperty("irrigationDate") var irrigationDate: String? = null,
    @JsonProperty("irrigationEndDate") var irrigationEndDate: String? = null,
    @JsonProperty("irrigationStatus") val irrigationStatus: Boolean? = null,
    @JsonProperty("location") val location: String? = null,

)

