package banquemisr.com.sensiorservice.model.dto

import banquemisr.com.sensiorservice.model.Land
import com.fasterxml.jackson.annotation.JsonProperty

data class IrrigateResponse(
    @JsonProperty("message") val message: String? = null,
    @JsonProperty("land") val land: Land? = null
)