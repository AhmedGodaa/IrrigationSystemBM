package banquemisr.com.irrigationsystem.usecases.land.deleteLand


import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.repositories.LandRepository
import banquemisr.com.irrigationsystem.validators.DeletionValidator
import org.springframework.stereotype.Service

@Service
class DeleteLandServiceImpl(
    private val deleteLandValidator: DeleteLandValidator,
    private val landRepository: LandRepository,
    private val deletionValidator: DeletionValidator<Land,String>
) : DeleteLandService {
    override fun deleteLand(deleteLandRequest: DeleteLandRequest?): DeleteLandResponse {
        deleteLandValidator.validate(deleteLandRequest)
        val landId = deleteLandRequest!!.id!!
        deletionValidator.validateExistence(landId, landRepository)
        deleteLandDB(landId)
        deletionValidator.validateDeletion(landId, landRepository)
        return DeleteLandResponse(
            message = "Land deleted successfully"
        )
    }


    private fun deleteLandDB(landId: String) {
        try {
            landRepository.deleteById(landId)
        } catch (e: Exception) {
            throw LandDeletionException("Error while deleting land from DB")
        }
    }


}




