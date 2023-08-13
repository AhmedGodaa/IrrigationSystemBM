package banquemisr.com.irrigationsystem.usecases.land.getLandById

import org.springframework.stereotype.Service

@Service
class GetLandByIDValidatorImpl : GetLandByIDValidator {
    override fun validateGetLandByIDRequest(findLandByIdRequest: GetLandByIDRequest?) {
        if (findLandByIdRequest == null) throw IllegalArgumentException("Land ID Can't be Null")
        if (findLandByIdRequest.id == null) throw IllegalArgumentException("Land ID Can't be Null")


    }
}