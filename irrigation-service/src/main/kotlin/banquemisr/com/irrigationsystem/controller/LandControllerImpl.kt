package banquemisr.com.irrigationsystem.controller

import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.model.dto.response.ChangeStatusResponse
import banquemisr.com.irrigationsystem.model.dto.response.GetLandByIDResponse
import banquemisr.com.irrigationsystem.usecases.land.changeStatus.ChangeStatusService
import banquemisr.com.irrigationsystem.usecases.land.createLand.CreateLandRequest
import banquemisr.com.irrigationsystem.usecases.land.createLand.CreateLandResponse
import banquemisr.com.irrigationsystem.usecases.land.createLand.CreateLandService
import banquemisr.com.irrigationsystem.usecases.land.deleteLand.DeleteLandRequest
import banquemisr.com.irrigationsystem.usecases.land.deleteLand.DeleteLandResponse
import banquemisr.com.irrigationsystem.usecases.land.deleteLand.DeleteLandService
import banquemisr.com.irrigationsystem.usecases.land.getLandById.GetLandByIDRequest
import banquemisr.com.irrigationsystem.usecases.land.getLandById.GetLandByIDService
import banquemisr.com.irrigationsystem.usecases.land.getLands.GetLandsRequest
import banquemisr.com.irrigationsystem.usecases.land.getLands.GetLandsResponse
import banquemisr.com.irrigationsystem.usecases.land.getLands.GetLandsService
import banquemisr.com.irrigationsystem.usecases.land.updateLand.UpdateLandRequest
import banquemisr.com.irrigationsystem.usecases.land.updateLand.UpdateLandResponse
import banquemisr.com.irrigationsystem.usecases.land.updateLand.UpdateLandService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller

@Controller
class LandControllerImpl(
    private val deleteLandService: DeleteLandService,
    private val updateLandService: UpdateLandService,
    private val createLandService: CreateLandService,
    private val changeStatusService: ChangeStatusService,
    private val getLandByIDService: GetLandByIDService,
    private val getLandsService: GetLandsService,
) : LandController {
    override fun getLandById(id: String?): ResponseEntity<GetLandByIDResponse> {
        return ResponseEntity.ok().body(getLandByIDService.getLandById(GetLandByIDRequest(id = id)))
    }

    override fun getLands(): ResponseEntity<GetLandsResponse> {
        return ResponseEntity.ok().body(getLandsService.getLands(GetLandsRequest()))
    }

    override fun updateLand(updateLandRequest: UpdateLandRequest?): ResponseEntity<UpdateLandResponse> {
        return ResponseEntity.ok().body(updateLandService.updateLand(updateLandRequest))
    }

    override fun deleteLand(deleteLandRequest: DeleteLandRequest?): ResponseEntity<DeleteLandResponse> {
        return ResponseEntity.ok().body(deleteLandService.deleteLand(deleteLandRequest))
    }


    override fun createLand(createLandRequest: CreateLandRequest?): ResponseEntity<CreateLandResponse> {
        return ResponseEntity.ok().body(createLandService.createLand(createLandRequest))

    }


    override fun changeStatus(changeStatusRequest: ChangeStatusRequest?): ResponseEntity<ChangeStatusResponse> {
        return ResponseEntity.ok().body(changeStatusService.changeStatus(changeStatusRequest))
    }


}