package banquemisr.com.irrigationsystem.usecases.land.createLand

import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.repositories.LandRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateLandServiceImpl(
    private val createLandValidator: CreateLandValidator,
    private val landRepository: LandRepository
) : CreateLandService {
    override fun createLand(createLandRequest: CreateLandRequest?): CreateLandResponse {
        createLandValidator.validateCreateLandRequest(createLandRequest)
        createLandRequest?.land
        val newLand = Land(
            id = UUID.randomUUID().toString(),

            )
        return CreateLandResponse(
            message = "Land created successfully",
            land = saveLandDB(newLand)
        )
    }


    fun saveLandDB(land: Land): Land {
        return try {
            landRepository.save(land)
        } catch (e: Exception) {
            throw Exception("Error while saving land to DB")
        }
    }
}