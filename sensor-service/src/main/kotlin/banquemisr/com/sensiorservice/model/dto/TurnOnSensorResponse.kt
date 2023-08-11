package banquemisr.com.sensiorservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TurnOnSensorResponse(
    @JsonProperty("message") val message: String? = null
)