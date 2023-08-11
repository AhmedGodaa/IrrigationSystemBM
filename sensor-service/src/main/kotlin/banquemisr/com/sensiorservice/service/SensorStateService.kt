package banquemisr.com.sensiorservice.service

import banquemisr.com.sensiorservice.model.SensorState
import banquemisr.com.sensiorservice.model.UserFCMToken
import banquemisr.com.sensiorservice.model.dto.TurnOffSensorResponse
import banquemisr.com.sensiorservice.model.dto.TurnOnSensorResponse
import banquemisr.com.sensiorservice.model.dto.UpdateUserFCMTokenResponse
import banquemisr.com.sensiorservice.repos.SensorStateRepo
import banquemisr.com.sensiorservice.repos.UserFCMTokenRepo
import banquemisr.com.sensiorservice.utils.Constants
import org.springframework.stereotype.Service

@Service
class SensorStateService(
    private val sensorStateRepo: SensorStateRepo,
    private val userFCMTokenRepo: UserFCMTokenRepo
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
                message = "Sensor is turned off"
            )
        } catch (e: Exception) {
            TurnOffSensorResponse(
                message = "Sensor is not turned off"
            )
        }
    }

    fun updateUserFCMToken(id: String): UpdateUserFCMTokenResponse? {
        return try {
            userFCMTokenRepo.save(UserFCMToken(Constants.USER_ID, id))
            UpdateUserFCMTokenResponse(
                message = "User FCM Token is updated"
            )
        } catch (e: Exception) {
            UpdateUserFCMTokenResponse(
                message = "User FCM Token is not updated"
            )
        }
    }

    fun saveSensorState(state: SensorState): SensorState {
        return sensorStateRepo.save(state)

    }
}