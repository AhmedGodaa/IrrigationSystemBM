package banquemisr.com.sensiorservice.service

import banquemisr.com.sensiorservice.events.GetAllLandsEvent
import banquemisr.com.sensiorservice.events.GetAllLandsResponseEvent
import banquemisr.com.sensiorservice.model.Land
import banquemisr.com.sensiorservice.model.dto.SendNotificationRequest
import banquemisr.com.sensiorservice.repos.SensorStateRepo
import banquemisr.com.sensiorservice.repos.UserFCMTokenRepo
import banquemisr.com.sensiorservice.utils.Constants
import banquemisr.com.sensiorservice.utils.KafkaFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*


@Service
class SensorService(
    private val sensorStateRepo: SensorStateRepo,
    private val userFCMTokenRepo: UserFCMTokenRepo,
    kafkaFactory: KafkaFactory
) {
    private val landsEventProducer: KafkaProducer<String, GetAllLandsEvent> = kafkaFactory.createProducer()
    private val landsEventConsumer: KafkaConsumer<String, GetAllLandsResponseEvent> =
        kafkaFactory.createConsumer("lands-event-consumer-group", "lands-event-topic")
    private val notificationProducer: KafkaProducer<String, SendNotificationRequest> = kafkaFactory.createProducer()
    private val irrigationMessageProducer: KafkaProducer<String, Land> = kafkaFactory.createProducer()
    private val logger = LoggerFactory.getLogger(SensorService::class.java)


    // Function to produce irrigation message to Kafka topic
    fun produceIrrigateMessage(land: Land) {
        val record = ProducerRecord("your_topic_name", "irrigate", land)
        irrigationMessageProducer.send(record)
    }

    fun produceLandsEvent(landsEvent: GetAllLandsEvent) {
        val record = ProducerRecord("your_topic_name", "landsEvent", landsEvent)
        logger.info("produce land event $landsEvent")
        landsEventProducer.send(record)
    }


    suspend fun sendNotification(sendNotificationRequest: SendNotificationRequest) {
        withContext(Dispatchers.IO) {
            notificationProducer.send(
                ProducerRecord(
                    "notification-topic",
                    null,
                    sendNotificationRequest
                )
            )
        }
    }


    suspend fun consumeLandsFromKafka(landsEventConsumer: KafkaConsumer<String, GetAllLandsResponseEvent>): List<Land> =
        withContext(Dispatchers.Default) {
            val lands = mutableListOf<Land>()
            val pollTimeout = Duration.ofMillis(1000) // Adjust the poll timeout as needed
            landsEventConsumer.use { landsEventConsumer ->
                val job = launch {

                    while (isActive) {
                        val records = landsEventConsumer.poll(pollTimeout)
                        for (record in records) {
                            val landResponse = record.value()
                            lands.addAll(landResponse.lands ?: emptyList())
                        }
                    }
                }

                job.join() // Wait for the job to complete
            }

            lands
        }


    @Scheduled(fixedRate = 60000 * 1)
    suspend fun irrigate() {
        if (withContext(Dispatchers.IO) {
                sensorStateRepo.findById(Constants.SENSOR_ID)
            }.get().state == true) {
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

            produceLandsEvent(landsEvent)
            val lands = consumeLandsFromKafka(landsEventConsumer)

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
                    recipeientToken = withContext(Dispatchers.IO) {
                        userFCMTokenRepo.findById(Constants.USER_ID)
                    }.get().token,
                    title = "Sensor is turned off",
                    body = "Sensor is turned off",
                    data = mutableMapOf("key" to "value")
                )
            )
        }
    }


}