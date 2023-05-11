package banquemisr.com.irrigationsystem.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.mapping.Document

@Document("land")
data class Land(
    @JsonProperty("id") val id: String? = null,
    @JsonProperty("name") val name: String? = null,
    @JsonProperty("area") val area: Double? = null,
    @JsonProperty("irrigationStatus") val irrigationStatus: Boolean? = null,
    @JsonProperty("irrigationDate") val irrigationDate: String? = null,
    @JsonProperty("irrigationEndDate") val irrigationEndDate: String? = null,
    @JsonProperty("location") val location: String? = null
)

