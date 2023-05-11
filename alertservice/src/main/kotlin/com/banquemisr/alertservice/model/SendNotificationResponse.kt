package com.banquemisr.alertservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendNotificationResponse
    (@JsonProperty("message") val message: String? = null)