package banquemisr.com.irrigationsystem.usecases.land.deleteLand

import banquemisr.com.irrigationsystem.dto.DeleteLandRequest
import org.springframework.stereotype.Component

@Component
class DeleteLandValidatorImpl: DeleteLandValidator {
    override fun validate(deleteLandRequest: DeleteLandRequest?) {
        if (deleteLandRequest == null) {
            throw LandDeletionException("DeleteLandRequest is null")
        }

    }

}