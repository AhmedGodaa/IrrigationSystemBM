package banquemisr.com.sensiorservice.events

import banquemisr.com.sensiorservice.model.Land
import com.fasterxml.jackson.annotation.JsonProperty

data class GetAllLandsResponseEvent(
    @JsonProperty("message") val message: String? = null,
    @JsonProperty("lands") val lands: MutableList<Land>? = null
)