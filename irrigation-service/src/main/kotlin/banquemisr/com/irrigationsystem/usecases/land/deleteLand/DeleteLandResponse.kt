package banquemisr.com.irrigationsystem.usecases.land.deleteLand

import com.fasterxml.jackson.annotation.JsonProperty

data class DeleteLandResponse(@JsonProperty("message") val message: String? = null)