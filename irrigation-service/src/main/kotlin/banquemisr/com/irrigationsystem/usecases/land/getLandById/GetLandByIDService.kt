package banquemisr.com.irrigationsystem.usecases.land.getLandById

import banquemisr.com.irrigationsystem.model.dto.response.GetLandByIDResponse

interface GetLandByIDService {
    fun getLandById(getLandByIdRequest: GetLandByIDRequest?): GetLandByIDResponse
}