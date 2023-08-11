package banquemisr.com.irrigationsystem.usecases.land.getAllLands

import banquemisr.com.irrigationsystem.model.Land
import org.springframework.stereotype.Component

@Component
class GetAllLandsValidatorImpl : GetAllLandsValidator {
    override fun validate(getAllLandsRequest: GetAllLandsRequest?) {
        if (getAllLandsRequest == null) {
            throw IllegalArgumentException("GetAllLandsRequest is null")
        }
    }
}

data class GetAllLandsResponse(
    val lands: List<Land>
)

class GetAllLandsRequest {

}