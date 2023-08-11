package banquemisr.com.irrigationsystem.model.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class DeleteLandResponse(@JsonProperty("message") val message: String? = null)