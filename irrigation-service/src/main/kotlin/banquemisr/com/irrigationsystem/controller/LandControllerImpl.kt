package banquemisr.com.irrigationsystem.controller

import banquemisr.com.irrigationsystem.model.dto.request.LandIrrigateRequest
import banquemisr.com.irrigationsystem.model.dto.response.*
import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.service.LandService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class LandControllerImpl(private val landService: LandService) : LandController {
    override fun getLandById(id: String?): ResponseEntity<GetLandByIDResponse> {
        return ResponseEntity.ok().body(landService.getLandById(id))
    }

    override fun getListOfLand(): ResponseEntity<GetListOfLandResponse> {
        return ResponseEntity.ok().body(landService.getListOfLand())
    }

    override fun updateLand(landRequest: CreateLandRequest?): ResponseEntity<UpdateLandResponse> {
        return ResponseEntity.ok().body(landService.updateLand(landRequest))
    }

    override fun deleteLand(id: String?): ResponseEntity<DeleteLandResponse> {
        return ResponseEntity.ok().body(landService.deleteLand(id))
    }

    override fun createLand(landRequest: CreateLandRequest?): ResponseEntity<CreateNewLandResponse> {
        return ResponseEntity.ok().body(landService.createLand(landRequest))

    }

    override fun irrigate(landIrrigateRequest: LandIrrigateRequest?): ResponseEntity<IrrigateResponse> {
        return ResponseEntity.ok().body(landService.irrigate(landIrrigateRequest))

    }

    override fun changeStatus(changeStatusRequest: ChangeStatusRequest?): ResponseEntity<ChangeStatusResponse> {
        return ResponseEntity.ok().body(landService.changeStatus(changeStatusRequest))
    }


}