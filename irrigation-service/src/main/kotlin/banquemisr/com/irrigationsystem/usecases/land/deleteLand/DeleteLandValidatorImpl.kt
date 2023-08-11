package banquemisr.com.irrigationsystem.usecases.land.deleteLand

import banquemisr.com.irrigationsystem.dto.DeleteLandRequest
import org.springframework.stereotype.Component

@Component
class DeleteLandValidatorImpl(
    private val deleteLandValidator: DeleteLandValidator
) {
    fun validateDeleteLandRequest(deleteLandRequest: DeleteLandRequest?) {
        if (deleteLandRequest == null) {
            throw IllegalArgumentException("DeleteLandRequest is null")
        }
        if (deleteLandRequest.id == null) {
            throw IllegalArgumentException("LandId is null")
        }
    }
}