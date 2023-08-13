package banquemisr.com.irrigationsystem.usecases.land.updateLand

import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.repositories.LandRepository
import org.springframework.stereotype.Service

@Service
class UpdateLandServiceImpl(
    private val updateLandValidator: UpdateLandValidator,
    private val landRepository: LandRepository
) : UpdateLandService {


    override fun updateLand(updateLandRequest: UpdateLandRequest?): UpdateLandResponse {
        updateLandValidator.validateUpdateLandRequest(updateLandRequest)
        val currentLand = findLandDB(updateLandRequest!!.land!!.id!!)
        val land = updateLandRequest.land!!
        val landToUpdate = currentLand.copy(


        )
        return UpdateLandResponse(
            message = "Land updated successfully",
            land = updateLandDB(landToUpdate)
        )
    }

    private fun findLandDB(landId: String): Land {
        return landRepository.findById(landId).orElseThrow {
            IllegalArgumentException("Land can't be fetched")
        }

    }

    private fun updateLandDB(land: Land): Land {
        return try {
            landRepository.save(land)
        } catch (e: Exception) {
            throw IllegalArgumentException("Land can't be updated")
        }

    }



}
