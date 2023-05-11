package banquemisr.com.irrigationsystem.model.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class ChangeStatusRequest(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("irrigationStatus") var irrigationStatus: Boolean? = null,
)