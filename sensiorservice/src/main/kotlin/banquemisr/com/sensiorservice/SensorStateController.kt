package banquemisr.com.sensiorservice

import banquemisr.com.sensiorservice.model.dto.UpdateUserFCMTokenResponse
import banquemisr.com.sensiorservice.model.dto.TurnOffSensorResponse
import banquemisr.com.sensiorservice.model.dto.TurnOnSensorResponse
import banquemisr.com.sensiorservice.service.SensorStateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SensorStateController(
    private val sensorStateService: SensorStateService
) {

    @PostMapping("/turnOnSensor")
    fun turnOnSensor(): ResponseEntity<TurnOnSensorResponse> {
        return ResponseEntity.ok().body(sensorStateService.turnOnSensor())
    }

    @PostMapping("/turnOffSensor")
    fun turnOffSensor(): ResponseEntity<TurnOffSensorResponse> {
        return ResponseEntity.ok().body(sensorStateService.turnOffSensor())
    }


    @PostMapping("/updateUserFCMToken")
    fun updateUserFCMToken(@RequestParam("token") token: String): ResponseEntity<UpdateUserFCMTokenResponse> {
        return ResponseEntity.ok().body(sensorStateService.updateUserFCMToken())
    }
}