package banquemisr.com.sensiorservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.mapping.Document

@Document("sensorState")
data class SensorState(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("state") val state: Boolean? = null
)