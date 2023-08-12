package banquemisr.com.irrigationsystem.usecases.land.createLand

interface CreateLandService {
    fun createLand(createLandRequest: CreateLandRequest?): CreateLandResponse
}

