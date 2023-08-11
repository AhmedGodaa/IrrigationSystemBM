package banquemisr.com.irrigationsystem.service

import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.model.dto.request.CreateLandRequest
import banquemisr.com.irrigationsystem.model.dto.request.LandIrrigateRequest
import banquemisr.com.irrigationsystem.model.dto.response.*
import banquemisr.com.irrigationsystem.repositories.LandRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class LandService(
    private val landRepository: LandRepository
) {
    fun getListOfLand(): GetListOfLandResponse {
        return if (landRepository.findAll().isEmpty()) {
            GetListOfLandResponse(
                message = "No Lands Found", lands = mutableListOf()
            )
        } else {
            GetListOfLandResponse(
                message = "All Lands Found Successfully", lands = landRepository.findAll()
            )
        }


    }



    fun deleteLand(id: String?): DeleteLandResponse {
        if (id != null) {
            return if (landRepository.findById(id).isPresent) {
                landRepository.deleteById(id)
                if (landRepository.findById(id).isPresent) {
                    DeleteLandResponse(
                        message = "Land Not Deleted ",
                    )
                } else {
                    DeleteLandResponse(
                        message = "Land Deleted Successfully",
                    )
                }
            } else {
                DeleteLandResponse(
                    message = "Land with this ID $id Not Found"
                )
            }
        } else {
            return DeleteLandResponse(
                message = "Please Enter Land ID"
            )
        }

    }

    fun createLand(landRequest: CreateLandRequest?): CreateNewLandResponse {
        if (landRequest != null) {
            if (landRequest.id == null) {
                landRequest.irrigationDate = Date().time.toString()
                landRequest.irrigationEndDate = Date().time.toString()
                landRequest.irrigationStatus = false
                if (landRequest.area == null) {
                    landRequest.area = 1.0
                }
                val land = landRepository.save(landRequest.toLand())
                return CreateNewLandResponse(
                    message = "Land Created Successfully", land = land
                )
            } else {
                return if (landRepository.findById(landRequest.id).isPresent) {
                    CreateNewLandResponse(
                        message = "Land Already Exist", land = landRepository.findById(landRequest.id).get()
                    )
                } else {
                    landRepository.save(landRequest.toLand())
                    CreateNewLandResponse(
                        message = "Land Created Successfully", land = landRepository.findById(landRequest.id).get()
                    )
                }
            }
        } else {
            return CreateNewLandResponse(
                message = "Land Body Can't be Empty"
            )
        }

    }

    fun irrigate(irrigateRequest: LandIrrigateRequest?): IrrigateResponse {
        if (irrigateRequest == null) {
            return IrrigateResponse(
                message = "Irrigate Request Can't be Empty"
            )
        } else {
            if (irrigateRequest.id == null) {
                return IrrigateResponse(
                    message = "Land ID Can't be Empty"
                )
            } else {
                return if (landRepository.findById(irrigateRequest.id).isPresent) {
                    val land = landRepository.findById(irrigateRequest.id).get()
                    IrrigateResponse(
                        message = "Land Irrigated Successfully", land = landRepository.save(irrigateRequest.toLand(land))
                    )
                } else {
                    IrrigateResponse(
                        message = "Land Not Found With This ID ${irrigateRequest.id}"
                    )
                }
            }
        }
    }

    fun changeStatus(changeStatusRequest: ChangeStatusRequest?): ChangeStatusResponse {
        if (changeStatusRequest == null) {
            return ChangeStatusResponse(
                message = "Irrigate Request Can't be Empty"
            )
        } else {
            if (changeStatusRequest.id == null) {
                return ChangeStatusResponse(
                    message = "Land ID Can't be Empty"
                )
            } else {
                return if (landRepository.findById(changeStatusRequest.id).isPresent) {
                    val land = landRepository.findById(changeStatusRequest.id).get()
                    ChangeStatusResponse(
                        message = "Land Irrigated Successfully", land = landRepository.save(changeStatusRequest.toLand(land))
                    )
                } else {
                    ChangeStatusResponse(
                        message = "Land Not Found With This ID ${changeStatusRequest.id}"
                    )
                }
            }
        }

    }

}

private fun ChangeStatusRequest.toLand(land: Land): Land {
    return Land(
        id = land.id,
        name = land.name,
        area = land.area,
        location = land.location,
        irrigationDate = land.irrigationDate,
        irrigationEndDate = land.irrigationEndDate,
        irrigationStatus = this.irrigationStatus,
    )

}


private fun LandIrrigateRequest.toLand(land: Land): Land {
    return Land(
        id = land.id,
        name = land.name,
        area = land.area,
        location = land.location,
        irrigationDate = this.irrigationDate,
        irrigationEndDate = this.irrigationEndDate,
        irrigationStatus = land.irrigationStatus,
    )


}


// showcase of using extension function kotlin
// we also can use .copy() function to copy the object and change the values we want

private fun CreateLandRequest.toLand(): Land {
    return Land(
        id = this.id,
        name = this.name,
        area = this.area,
        location = this.location,
        irrigationDate = this.irrigationDate,
        irrigationEndDate = this.irrigationEndDate,
        irrigationStatus = this.irrigationStatus
    )


}


