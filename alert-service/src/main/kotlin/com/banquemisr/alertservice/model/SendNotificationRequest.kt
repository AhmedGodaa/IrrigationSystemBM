package com.banquemisr.alertservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SendNotificationRequest(
    @JsonProperty("recipeientToken") val recipeientToken: String? = null,
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("body") val body: String? = null,
    @JsonProperty("data") val data: Map<String, String>? = null
)