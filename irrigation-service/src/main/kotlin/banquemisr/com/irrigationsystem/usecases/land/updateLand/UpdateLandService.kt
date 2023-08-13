package banquemisr.com.irrigationsystem.usecases.land.updateLand;

interface UpdateLandService {
    fun updateLand(updateLandRequest: UpdateLandRequest?): UpdateLandResponse
}