package banquemisr.com.irrigationsystem.usecases.land.deleteLand

import banquemisr.com.irrigationsystem.dto.DeleteLandRequest
import banquemisr.com.irrigationsystem.model.dto.response.DeleteLandResponse
import org.springframework.stereotype.Service

@Service
class DeleteLandServiceImpl : DeleteLandService {
    override fun deleteLand(landId: Long?): DeleteLandResponse {
        TODO("Not yet implemented")
    }
}