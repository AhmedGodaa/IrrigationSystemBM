package banquemisr.com.irrigationsystem.usecases.land.getLands

import banquemisr.com.irrigationsystem.model.Land

data class GetLandsResponse(
    val lands: List<Land>
)

