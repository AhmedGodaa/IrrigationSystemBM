package banquemisr.com.sensiorservice.service

import banquemisr.com.sensiorservice.model.SensorState
import banquemisr.com.sensiorservice.model.dto.TurnOffSensorResponse
import banquemisr.com.sensiorservice.model.dto.TurnOnSensorResponse
import banquemisr.com.sensiorservice.model.dto.UpdateUserFCMTokenResponse
import banquemisr.com.sensiorservice.repos.SensorStateRepo
import banquemisr.com.sensiorservice.utils.Constants
import org.springframework.stereotype.Service

@Service
class SensorStateService(
    private val sensorStateRepo: SensorStateRepo
) {
    fun turnOnSensor(): TurnOnSensorResponse {
        return try {
            sensorStateRepo.save(SensorState(Constants.SENSOR_ID, true))
            TurnOnSensorResponse(
                message = "Sensor is turned on"
            )
        } catch (e: Exception) {
            TurnOnSensorResponse(
                message = "Sensor is not turned on"
            )
        }

    }

    fun turnOffSensor(): TurnOffSensorResponse {
        return try {
            sensorStateRepo.save(SensorState(Constants.SENSOR_ID, false))
            TurnOffSensorResponse(
                message = "Sensor is turned on"
            )
        } catch (e: Exception) {
            TurnOffSensorResponse(
                message = "Sensor is not turned off"
            )
        }
    }

    fun updateUserFCMToken(): UpdateUserFCMTokenResponse? {
        return try {
            sensorStateRepo.save(SensorState(Constants.SENSOR_ID, false))
            UpdateUserFCMTokenResponse(
                message = "Sensor is turned on"
            )
        } catch (e: Exception) {
            UpdateUserFCMTokenResponse(
                message = "Sensor is not turned off"
            )
        }
    }
}