package banquemisr.com.irrigationsystem.usecases.land.deleteLand

import banquemisr.com.irrigationsystem.dto.DeleteLandRequest
import banquemisr.com.irrigationsystem.model.dto.response.DeleteLandResponse

interface DeleteLandService {
    fun deleteLand(deleteLandRequest: DeleteLandRequest): DeleteLandResponse
}