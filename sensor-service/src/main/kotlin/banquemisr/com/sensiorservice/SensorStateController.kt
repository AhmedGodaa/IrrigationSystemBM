package banquemisr.com.sensiorservice

import banquemisr.com.sensiorservice.model.SensorState
import banquemisr.com.sensiorservice.model.dto.UpdateUserFCMTokenResponse
import banquemisr.com.sensiorservice.model.dto.TurnOffSensorResponse
import banquemisr.com.sensiorservice.model.dto.TurnOnSensorResponse
import banquemisr.com.sensiorservice.service.SensorStateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class SensorStateController(
    private val sensorStateService: SensorStateService
) {
    @PostMapping("/saveSensorState")
    fun saveSensorState(@RequestBody state: SensorState): ResponseEntity<SensorState> {
        return ResponseEntity.ok().body(sensorStateService.saveSensorState(state))
    }

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
        return ResponseEntity.ok().body(sensorStateService.updateUserFCMToken(token))
    }
}