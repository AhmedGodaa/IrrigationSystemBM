package banquemisr.com.irrigationsystem.model.dto.response

import banquemisr.com.irrigationsystem.model.Land
import com.fasterxml.jackson.annotation.JsonProperty

data class GetListOfLandResponse(
    @JsonProperty("message") val message: String? = null,
    @JsonProperty("lands") val lands: MutableList<Land>? = null
)