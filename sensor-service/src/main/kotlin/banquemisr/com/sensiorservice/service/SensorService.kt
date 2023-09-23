package banquemisr.com.sensiorservice.service

import banquemisr.com.sensiorservice.model.Land
import banquemisr.com.sensiorservice.model.dto.GetAllLandsEvent
import banquemisr.com.sensiorservice.model.dto.GetAllLandsResponseEvent
import banquemisr.com.sensiorservice.model.dto.SendNotificationRequest
import banquemisr.com.sensiorservice.repos.SensorStateRepo
import banquemisr.com.sensiorservice.repos.UserFCMTokenRepo
import banquemisr.com.sensiorservice.utils.Constants
import banquemisr.com.sensiorservice.utils.KafkaFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*


@Service
class SensorService(
    private val sensorStateRepo: SensorStateRepo,
    private val userFCMTokenRepo: UserFCMTokenRepo,
    @Value("spring.kafka.bootstrap-servers") private val bootstrapServers: String
    private val kafkaFactory: KafkaFactory
) {

    val landsEventProducer: KafkaProducer<String, GetAllLandsEvent> = kafkaFactory.createProducer()
    val landsEventConsumer: KafkaConsumer<String, GetAllLandsResponseEvent> =
        kafkaFactory.createConsumer(
            "lands-event-consumer-group",
            "lands-event-topic",
        )
    val notificationProducer: KafkaProducer<String, GetAllLandsEvent> = kafkaFactory.createProducer()
    val notificationConsumer: KafkaConsumer<String, Any> =
        kafkaFactory.createConsumer(
            "notification-consumer-group",
            "notification-response-topic",
        )
    val irrigationMessageProducer: KafkaProducer<String, Land> = kafkaFactory.createProducer()



    // Function to produce lands event to Kafka topic


    // Function to produce irrigation message to Kafka topic
    fun produceIrrigateMessage(land: Land) {
        val record = ProducerRecord("your_topic_name", "irrigate", land)
        irrigationMessageProducer.send(record)
    }

    fun produceLandsEvent(landsEvent: GetAllLandsEvent) {
        val record = ProducerRecord("your_topic_name", "landsEvent", landsEvent)
        landsEventProducer.send(record)
    }
    fun consumeLandsFromKafka(): List<Land> {
        val lands = mutableListOf<Land>()
        val records = landsEventConsumer.poll(Duration.ofMillis(1000))

        for (record in records) {
            val landResponse = record.value()
            lands.addAll(landResponse.lands ?: emptyList())
        }

        return lands
    }


    @Scheduled(fixedRate = 60000 * 1)
    fun irrigate() {
        if (sensorStateRepo.findById(Constants.SENSOR_ID).get().state == true) {
            val landsEvent = GetAllLandsEvent(
                lands = listOf(
                    Land(
                        id = "1",
                        irrigationStatus = true,
                        irrigationDate = "1627776000000",
                        irrigationEndDate = "1627776000000",
                        area = 1.0
                    ),
                ),
                message = "Get All Lands",
                status = 200
            )

            // Produce lands event to Kafka topic
            produceLandsEvent(landsEvent)

            // Consume lands from Kafka topic
            val lands = consumeLandsFromKafka()

            lands.forEach { land ->
                if (Date().time.toString()
                        .toLong() >= (land.irrigationEndDate)?.toLong()!! && land.irrigationStatus == true
                ) {
                    println("Irrigating land with ID ${land.id}")
                    land.irrigationDate = Date().time.toString()
                    land.irrigationEndDate = (Date().time + (1000) * 60L * land.area!!).toString()

                    // Perform irrigation by producing a message to Kafka topic
                    produceIrrigateMessage(land)
                } else {
                    println("Irrigation Did Not Expire Yet Or Irrigation Is Turned OFF")
                }
            }
        } else {
            println("Notification Alert is being sent")
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


//    fun sendNotification(sendNotificationRequest: SendNotificationRequest): SendNotificationResponse {
//        val headers = HttpHeaders()
//        headers.contentType = MediaType.APPLICATION_JSON
//        val url = URI("${alertServiceUrl}/api/sendNotification")
//        val restTemplate = RestTemplate()
//        val requestEntity: HttpEntity<SendNotificationRequest> = HttpEntity(sendNotificationRequest, headers)
//        val responseEntity: ResponseEntity<SendNotificationResponse> =
//            restTemplate.postForEntity(url, requestEntity, SendNotificationResponse::class.java)
//        return responseEntity.body as SendNotificationResponse
//
//
//    }

    suspend fun sendNotification(sendNotificationRequest: SendNotificationRequest) {

        try {
            // Produce message to Kafka topic
            notificationProducer.send(ProducerRecord("notification-topic", null, sendNotificationRequest)).get()

            // Asynchronously handle response
            val job = CoroutineScope(Dispatchers.Default).launch {
                notificationConsumer.subscribe(listOf("notification-response-topic"))

                while (isActive) {
                    val records = notificationConsumer.poll(Duration.ofMillis(100))
                    for (record in records) {
                        if (record.value().id == sendNotificationRequest.id) {
                            // Found matching response
                            val response = record.value()
                            // Do something with the response
                            notificationProducer.close()
                            notificationConsumer.close()
                            return@launch
                        }
                    }
                }
            }

            job.join()
        } finally {
            notificationProducer.close()
            notificationConsumer.close()
        }
    }

}