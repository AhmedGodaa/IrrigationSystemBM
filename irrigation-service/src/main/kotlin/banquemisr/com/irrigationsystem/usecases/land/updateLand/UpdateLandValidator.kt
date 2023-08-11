package banquemisr.com.irrigationsystem.usecases.land.updateLand

import banquemisr.com.irrigationsystem.dto.UpdateLandRequest
import banquemisr.com.irrigationsystem.model.dto.response.UpdateLandResponse

interface UpdateLandValidator {
    fun validateUpdateLandRequest(updateLandRequest: UpdateLandRequest?)
}