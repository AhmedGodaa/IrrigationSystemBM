package banquemisr.com.irrigationsystem.controller

import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.model.dto.response.ChangeStatusResponse
import banquemisr.com.irrigationsystem.model.dto.response.GetLandByIDResponse
import banquemisr.com.irrigationsystem.usecases.land.createLand.CreateLandRequest
import banquemisr.com.irrigationsystem.usecases.land.createLand.CreateLandResponse
import banquemisr.com.irrigationsystem.usecases.land.deleteLand.DeleteLandRequest
import banquemisr.com.irrigationsystem.usecases.land.deleteLand.DeleteLandResponse
import banquemisr.com.irrigationsystem.usecases.land.getLands.GetLandsResponse
import banquemisr.com.irrigationsystem.usecases.land.updateLand.UpdateLandRequest
import banquemisr.com.irrigationsystem.usecases.land.updateLand.UpdateLandResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/api")
interface LandController {
    @CrossOrigin(origins = ["*"])
    @GetMapping("/getLandById")
    fun getLandById(@RequestParam("id", required = false) id: String?): ResponseEntity<GetLandByIDResponse>

    @CrossOrigin(origins = ["*"])
    @GetMapping("/getLands")
    fun getLands(): ResponseEntity<GetLandsResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/updateLand")
    fun updateLand(@RequestBody(required = false) updateLandRequest: UpdateLandRequest?): ResponseEntity<UpdateLandResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/deleteLand")
    fun deleteLand(@RequestBody deleteLandRequest: DeleteLandRequest?): ResponseEntity<DeleteLandResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/createLand")
    fun createLand(@RequestBody(required = false) createLandRequest: CreateLandRequest?): ResponseEntity<CreateLandResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/changeStatus")
    fun changeStatus(@RequestBody(required = false) changeStatusRequest: ChangeStatusRequest?): ResponseEntity<ChangeStatusResponse>


}