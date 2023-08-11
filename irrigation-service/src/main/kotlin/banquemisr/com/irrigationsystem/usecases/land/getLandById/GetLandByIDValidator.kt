package banquemisr.com.irrigationsystem.usecases.land.getLandById

import banquemisr.com.irrigationsystem.model.dto.response.GetLandByIDResponse

interface GetLandByIDValidator {
    fun validateGetLandByIDRequest(findLandByIdRequest: GetLandByIDRequest?)
}

