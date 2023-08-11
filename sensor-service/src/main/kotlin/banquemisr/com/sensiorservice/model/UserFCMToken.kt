package banquemisr.com.sensiorservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class UserFCMToken(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("token") val token: String? = null,
)
