package banquemisr.com.irrigationsystem.usecases.land.deleteLand

import banquemisr.com.irrigationsystem.model.dto.response.DeleteLandResponse

interface DeleteLandService {
    fun deleteLand(landId: Long?) : DeleteLandResponse
}