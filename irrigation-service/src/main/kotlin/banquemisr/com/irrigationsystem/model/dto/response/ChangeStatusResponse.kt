package banquemisr.com.irrigationsystem.model.dto.response

import banquemisr.com.irrigationsystem.model.Land
import com.fasterxml.jackson.annotation.JsonProperty

data class ChangeStatusResponse(
    @JsonProperty("message") val message: String? = null,
    @JsonProperty("land") val land: Land? = null
)