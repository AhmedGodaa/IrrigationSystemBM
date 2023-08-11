package banquemisr.com.irrigationsystem.usecases.land.getLandById

import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.model.dto.response.GetLandByIDResponse
import banquemisr.com.irrigationsystem.repositories.LandRepository
import org.springframework.stereotype.Service

@Service
class GetLandByIdServiceImpl(
    private val getLandByIDValidator: GetLandByIDValidator,
    private val landRepository: LandRepository
) : GetLandByIDService {


    override fun getLandById(getLandByIdRequest: GetLandByIDRequest?): GetLandByIDResponse {
        getLandByIDValidator.validateGetLandByIDRequest(getLandByIdRequest)
        return GetLandByIDResponse(
            message = "Land fetched successfully",
            land = findLandByIdFromDB(getLandByIdRequest!!.id!!)
        )

    }

    private fun findLandByIdFromDB(id: String): Land {
        return landRepository.findById(id).orElseThrow {
            IllegalArgumentException("Land can't be fetched")
        }
    }


}