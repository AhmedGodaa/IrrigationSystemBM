package banquemisr.com.irrigationsystem.usecases.land.deleteLand

interface DeleteLandService {
    fun deleteLand(deleteLandRequest: DeleteLandRequest?): DeleteLandResponse
}