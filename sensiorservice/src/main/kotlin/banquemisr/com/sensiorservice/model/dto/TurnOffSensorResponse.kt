package banquemisr.com.sensiorservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TurnOffSensorResponse(
    @JsonProperty("message") val message: String? = null

)
