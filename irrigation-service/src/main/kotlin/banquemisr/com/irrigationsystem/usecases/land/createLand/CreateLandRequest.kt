package banquemisr.com.irrigationsystem.usecases.land.createLand

import banquemisr.com.irrigationsystem.model.Land
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateLandRequest(
    @JsonProperty("land") var land: Land? = null,
)