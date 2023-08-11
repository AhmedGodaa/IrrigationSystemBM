package banquemisr.com.irrigationsystem.controller

import banquemisr.com.irrigationsystem.model.dto.*
import banquemisr.com.irrigationsystem.model.dto.request.CreateLandRequest
import banquemisr.com.irrigationsystem.model.dto.request.LandIrrigateRequest
import banquemisr.com.irrigationsystem.model.dto.response.*
import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
interface LandController {
    @CrossOrigin(origins = ["*"])
    @GetMapping("/getLandById")
    fun getLandById(@RequestParam("id", required = false) id: String?): ResponseEntity<GetLandByIDResponse>

    @CrossOrigin(origins = ["*"])
    @GetMapping("/getAllLands")
    fun getListOfLand(): ResponseEntity<GetListOfLandResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/updateLand")
    fun updateLand(@RequestBody(required = false) landRequest: CreateLandRequest?): ResponseEntity<UpdateLandResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/deleteLand")
    fun deleteLand(@RequestParam("id", required = false) id: String?): ResponseEntity<DeleteLandResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/createLand")
    fun createLand(@RequestBody(required = false) landRequest: CreateLandRequest?): ResponseEntity<CreateNewLandResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/irrigate")
    fun irrigate(@RequestBody(required = false) landIrrigateRequest: LandIrrigateRequest?): ResponseEntity<IrrigateResponse>

    @CrossOrigin(origins = ["*"])
    @PostMapping("/changeStatus")
    fun changeStatus(@RequestBody(required = false) changeStatusRequest: ChangeStatusRequest?): ResponseEntity<ChangeStatusResponse>


}