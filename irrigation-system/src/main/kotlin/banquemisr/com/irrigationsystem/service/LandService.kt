package banquemisr.com.irrigationsystem.service

import banquemisr.com.irrigationsystem.model.Land
import banquemisr.com.irrigationsystem.model.dto.request.ChangeStatusRequest
import banquemisr.com.irrigationsystem.model.dto.request.CreateLandRequest
import banquemisr.com.irrigationsystem.model.dto.request.LandIrrigateRequest
import banquemisr.com.irrigationsystem.model.dto.response.*
import banquemisr.com.irrigationsystem.repositories.LandRepo
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class LandService(
    private val landRepo: LandRepo
) {
    fun getListOfLand(): GetListOfLandResponse {
        return if (landRepo.findAll().isEmpty()) {
            GetListOfLandResponse(
                message = "No Lands Found", lands = mutableListOf()
            )
        } else {
            GetListOfLandResponse(
                message = "All Lands Found Successfully", lands = landRepo.findAll()
            )
        }


    }

    fun getLandById(id: String?): GetLandByIDResponse {
        return if (id != null) {
            if (landRepo.findById(id).isPresent) {
                GetLandByIDResponse(
                    message = "Land Found Successfully", land = landRepo.findById(id).get()
                )
            } else {
                GetLandByIDResponse(
                    message = "Land Was Not Found With ID $id"
                )
            }
        } else {
            GetLandByIDResponse(
                message = "Land ID can't be Empty"
            )
        }


    }

    fun updateLand(landRequest: CreateLandRequest?): UpdateLandResponse {
        return if (landRequest != null) {
            if (landRepo.findById(landRequest.id!!).isPresent) {
                UpdateLandResponse(
                    message = "Land Updated Successfully", land = landRepo.save(landRequest.toLand())
                )
            } else {
                UpdateLandResponse(
                    message = "No Land was Found With This ID ${landRequest.id}"
                )
            }
        } else {
            UpdateLandResponse(
                message = "Land Body Can't be Empty"
            )
        }

    }

    fun deleteLand(id: String?): DeleteLandResponse {
        if (id != null) {
            return if (landRepo.findById(id).isPresent) {
                landRepo.deleteById(id)
                if (landRepo.findById(id).isPresent) {
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
                landRequest.irrigationDate = LocalTime.now().toString()
                landRequest.irrigationEndDate = LocalTime.now().toString()
                landRequest.irrigationStatus = false
                if (landRequest.area == null) {
                    landRequest.area = 1.0
                }
                val land = landRepo.save(landRequest.toLand())
                return CreateNewLandResponse(
                    message = "Land Created Successfully", land = land
                )
            } else {
                return if (landRepo.findById(landRequest.id).isPresent) {
                    CreateNewLandResponse(
                        message = "Land Already Exist", land = landRepo.findById(landRequest.id).get()
                    )
                } else {
                    landRepo.save(landRequest.toLand())
                    CreateNewLandResponse(
                        message = "Land Created Successfully", land = landRepo.findById(landRequest.id).get()
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
                return if (landRepo.findById(irrigateRequest.id).isPresent) {
                    val land = landRepo.findById(irrigateRequest.id).get()
                    IrrigateResponse(
                        message = "Land Irrigated Successfully", land = landRepo.save(irrigateRequest.toLand(land))
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
                return if (landRepo.findById(changeStatusRequest.id).isPresent) {
                    val land = landRepo.findById(changeStatusRequest.id).get()
                    ChangeStatusResponse(
                        message = "Land Irrigated Successfully", land = landRepo.save(changeStatusRequest.toLand(land))
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


