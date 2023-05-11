package banquemisr.com.irrigationsystem.model.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class LandIrrigateRequest(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("irrigationDate") val irrigationDate: String? = null,
    @JsonProperty("irrigationEndDate") val irrigationEndDate: String? = null
)
