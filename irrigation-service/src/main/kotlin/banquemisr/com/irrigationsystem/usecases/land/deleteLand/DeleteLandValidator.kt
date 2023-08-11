package banquemisr.com.irrigationsystem.usecases.land.deleteLand

import banquemisr.com.irrigationsystem.dto.DeleteLandRequest

interface DeleteLandValidator {
    fun validate(deleteLandRequest: DeleteLandRequest?)

}