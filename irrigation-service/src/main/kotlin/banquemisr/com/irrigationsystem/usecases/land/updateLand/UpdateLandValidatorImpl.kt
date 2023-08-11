package banquemisr.com.irrigationsystem.usecases.land.updateLand

import banquemisr.com.irrigationsystem.dto.UpdateLandRequest
import org.springframework.stereotype.Component

@Component
class UpdateLandValidatorImpl(
    private val updateLandValidator: UpdateLandValidator
) {
    fun validateUpdateLandRequest(updateLandRequest: UpdateLandRequest?) {
        if (updateLandRequest == null) { throw IllegalArgumentException("UpdateLandRequest is null") }
        if (updateLandRequest.land == null) { throw IllegalArgumentException("LandId is null") }
        if (updateLandRequest.land!!.id == null) { throw IllegalArgumentException("LandId is null") }
    }
}