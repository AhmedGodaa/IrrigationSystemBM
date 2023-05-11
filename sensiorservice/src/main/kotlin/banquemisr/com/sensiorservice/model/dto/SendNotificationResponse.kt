package banquemisr.com.sensiorservice.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SendNotificationResponse(@JsonProperty("message") val message: String? = null)