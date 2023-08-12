package banquemisr.com.irrigationsystem.usecases.land.createLand

import banquemisr.com.irrigationsystem.model.Land
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateLandResponse(
    @JsonProperty("message") var message: String? = null,
    @JsonProperty("land") var land: Land? = null
)
