package banquemisr.com.sensiorservice.service

import banquemisr.com.sensiorservice.model.dto.GetListOfLandResponse
import banquemisr.com.sensiorservice.model.dto.IrrigateResponse
import banquemisr.com.sensiorservice.model.dto.SendNotificationRequest
import banquemisr.com.sensiorservice.model.dto.SendNotificationResponse
import banquemisr.com.sensiorservice.repos.SensorStateRepo
import banquemisr.com.sensiorservice.repos.UserFCMTokenRepo
import banquemisr.com.sensiorservice.utils.Constants
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.*


@Service
class SensorService(
    private val restTemplate: RestTemplate,
    private val sensorStateRepo: SensorStateRepo,
    private val userFCMTokenRepo: UserFCMTokenRepo,
    @Value("service.irrigation.cluster.url") private val irrigationServiceUrl: String,
    @Value("service.alert.cluster.url") private val alertServiceUrl: String
) {

    @Scheduled(fixedRate = 60000 * 1)
    fun irrigate() {
        if (sensorStateRepo.findById(Constants.SENSOR_ID).get().state == true) {
            val responseEntity: ResponseEntity<*> = restTemplate.getForEntity(
                "${irrigationServiceUrl}/api/getAllLands",
                GetListOfLandResponse::class.java
            )

            val lands = responseEntity.body as GetListOfLandResponse
            lands.lands?.forEach {
                if ((Date().time.toString()).toLong() >= (it.irrigationEndDate)?.toLong()!! && it.irrigationStatus == true
                ) {
                    println("irrigating land with id ${it.id}")
                    it.irrigationDate = Date().time.toString()
                    it.irrigationEndDate = (Date().time + ((1000) * 60L * it.area!!)).toString()
                    restTemplate.postForEntity(
                        "$/api/irrigate",
                        it,
                        IrrigateResponse::class.java
                    )
                } else {
                    println("Irrigation Did Not Expire Yet Or Irrigation Is Turned OFF")
                }

            }

        } else {
            println("Notification Alert is been sent")
            sendNotification(
                SendNotificationRequest(
                    recipeientToken = userFCMTokenRepo.findById(Constants.USER_ID).get().token,
                    title = "Sensor is turned off",
                    body = "Sensor is turned off",
                    data = mutableMapOf("key" to "value")
                )
            )
        }

    }


    fun sendNotification(sendNotificationRequest: SendNotificationRequest): SendNotificationResponse {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val url = URI("${alertServiceUrl}/api/sendNotification")
        val restTemplate = RestTemplate()
        val requestEntity: HttpEntity<SendNotificationRequest> = HttpEntity(sendNotificationRequest, headers)
        val responseEntity: ResponseEntity<SendNotificationResponse> =
            restTemplate.postForEntity(url, requestEntity, SendNotificationResponse::class.java)
        return responseEntity.body as SendNotificationResponse


    }


}