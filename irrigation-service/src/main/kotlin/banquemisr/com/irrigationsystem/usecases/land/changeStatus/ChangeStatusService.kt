package banquemisr.com.irrigationsystem.usecases.land.changeStatus

import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.model.dto.response.ChangeStatusResponse

interface ChangeStatusService {
    fun changeStatus(changeStatusRequest: ChangeStatusRequest?): ChangeStatusResponse
}