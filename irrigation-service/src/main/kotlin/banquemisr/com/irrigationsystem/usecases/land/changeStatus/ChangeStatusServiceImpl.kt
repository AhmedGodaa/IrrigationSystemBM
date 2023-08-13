package banquemisr.com.irrigationsystem.usecases.land.changeStatus

import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.model.dto.response.ChangeStatusResponse
import org.springframework.stereotype.Service

@Service
class ChangeStatusServiceImpl : ChangeStatusService {
    override fun changeStatus(changeStatusRequest: ChangeStatusRequest?): ChangeStatusResponse {
        TODO("Not yet implemented")
    }
}