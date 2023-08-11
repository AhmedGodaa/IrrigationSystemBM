package banquemisr.com.irrigationsystem.usecases.land.updateLand

import banquemisr.com.irrigationsystem.dto.UpdateLandRequest
import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.model.dto.response.UpdateLandResponse
import banquemisr.com.irrigationsystem.repositories.LandRepository

class UpdateLandServiceImpl(
    private val updateLandService: UpdateLandService,
    private val updateLandValidator: UpdateLandValidator,
    private val landRepository: LandRepository
) {

    fun updateLand(updateLandRequest: UpdateLandRequest?): UpdateLandResponse {
        updateLandValidator.validateUpdateLandRequest(updateLandRequest)
        val currentLand = findLandDB(updateLandRequest!!.land!!.id!!)
        val land = updateLandRequest.land!!
        val landToUpdate = currentLand.copy(
            name = land.name,
            area = land.area,
            irrigationStatus = land.irrigationStatus,
            irrigationDate = land.irrigationDate,
            irrigationEndDate = land.irrigationEndDate,
            location = land.location

        )
        return UpdateLandResponse(
            message = "Land updated successfully",
            land = updateLand(landToUpdate)
        )
    }

    private fun findLandDB(landId: String): Land {
        return landRepository.findById(landId).orElseThrow {
            IllegalArgumentException("Land can't be fetched")
        }

    }

    private fun updateLand(land: Land): Land {
        return try {
            landRepository.save(land)
        } catch (e: Exception) {
            throw IllegalArgumentException("Land can't be updated")
        }

    }

}