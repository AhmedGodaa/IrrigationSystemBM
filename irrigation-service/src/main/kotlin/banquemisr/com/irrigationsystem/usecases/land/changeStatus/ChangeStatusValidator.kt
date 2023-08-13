package banquemisr.com.irrigationsystem.usecases.land.changeStatus

import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest

interface ChangeStatusValidator {
    fun validate(changeStatusRequest: ChangeStatusRequest?)
}