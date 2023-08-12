package banquemisr.com.irrigationsystem.usecases.land.createLand

interface CreateLandValidator {
    fun validateCreateLandRequest(createLandRequest: CreateLandRequest?)
}