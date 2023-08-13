package banquemisr.com.irrigationsystem.usecases.land.updateLand;

import org.springframework.stereotype.Component

@Component
class UpdateLandValidatorImpl : UpdateLandValidator {
    override fun validateUpdateLandRequest(updateLandRequest: UpdateLandRequest?) {
        if (updateLandRequest == null) {
            throw IllegalArgumentException("UpdateLandRequest is null")
        }
    }
}