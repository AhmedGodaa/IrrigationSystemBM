package banquemisr.com.sensiorservice.model.dto

import banquemisr.com.sensiorservice.model.Land

data class GetAllLandsEvent(
    val lands: List<Land>,
    val message: String,
    val status: Int,
)
