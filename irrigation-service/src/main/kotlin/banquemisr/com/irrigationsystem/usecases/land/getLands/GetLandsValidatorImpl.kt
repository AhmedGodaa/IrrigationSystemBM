package banquemisr.com.irrigationsystem.usecases.land.getLands

import org.springframework.stereotype.Component

@Component
class GetLandsValidatorImpl : GetLandsValidator {
    override fun validate(getAllLandsRequest: GetLandsRequest?) {
        if (getAllLandsRequest == null) {
            throw IllegalArgumentException("GetAllLandsRequest is null")
        }
    }
}

