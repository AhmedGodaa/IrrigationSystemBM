package banquemisr.com.sensiorservice.service

import banquemisr.com.sensiorservice.model.Land
import banquemisr.com.sensiorservice.model.dto.GetAllLandsEvent
import banquemisr.com.sensiorservice.model.dto.GetAllLandsResponseEvent
import banquemisr.com.sensiorservice.model.dto.SendNotificationRequest
import banquemisr.com.sensiorservice.model.dto.SendNotificationResponse
import banquemisr.com.sensiorservice.repos.SensorStateRepo
import banquemisr.com.sensiorservice.repos.UserFCMTokenRepo
import banquemisr.com.sensiorservice.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*


@Service
class SensorService(
    private val sensorStateRepo: SensorStateRepo,
    private val userFCMTokenRepo: UserFCMTokenRepo,
    @Value("spring.kafka.bootstrap-servers") private val bootstrapServers: String,
    @Value("service.alert.cluster.url") private val alertServiceUrl: String
) {

    val kafkaProducer: KafkaProducer<String, GetAllLandsEvent> = createKafkaProducer()
    val kafkaConsumer: KafkaConsumer<String, GetAllLandsResponseEvent> = createKafkaConsumer()


    private final fun createKafkaProducer(): KafkaProducer<String, GetAllLandsEvent> {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        return KafkaProducer(props)
    }

    private final fun createKafkaConsumer(): KafkaConsumer<String, GetAllLandsResponseEvent> {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java.name
        props[ConsumerConfig.GROUP_ID_CONFIG] = "your_group_id"
        val consumer = KafkaConsumer<String, GetAllLandsResponseEvent>(props)
        consumer.subscribe(listOf("your_topic_name"))
        return consumer
    }

    private fun kafkaNotificationProducer(): KafkaProducer<String, SendNotificationRequest> {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "your-bootstrap-server"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = SendNotificationRequestSerializer::class.java.name
        return KafkaProducer(props)
    }

    private fun kafkaNotificationConsumer(): KafkaConsumer<String, SendNotificationResponse> {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "your-bootstrap-server"
        props[ConsumerConfig.GROUP_ID_CONFIG] = "notification-consumer-group"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = SendNotificationResponseDeserializer::class.java.name
        return KafkaConsumer(props)
    }

    // Function to produce lands event to Kafka topic
    fun produceLandsEvent(landsEvent: GetAllLandsEvent) {
        val record = ProducerRecord("your_topic_name", "landsEvent", landsEvent)
        kafkaProducer.send(record)
    }

    // Function to produce irrigation message to Kafka topic
    fun produceIrrigateMessage(land: Land) {
        val record = ProducerRecord("your_topic_name", "irrigate", land)
        kafkaProducer.send(record)
    }

    fun consumeLandsFromKafka(): List<Land> {
        val lands = mutableListOf<Land>()
        val records = kafkaConsumer.poll(Duration.ofMillis(1000))

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
        val producer = createKafkaProducer()
        val consumer = createKafkaConsumer()

        try {
            // Produce message to Kafka topic
            producer.send(ProducerRecord("notification-topic", null, sendNotificationRequest)).get()

            // Asynchronously handle response
            val job = CoroutineScope(Dispatchers.Default).launch {
                consumer.subscribe(listOf("notification-response-topic"))

                while (isActive) {
                    val records = consumer.poll(Duration.ofMillis(100))
                    for (record in records) {
                        if (record.value().id == sendNotificationRequest.id) {
                            // Found matching response
                            val response = record.value()
                            // Do something with the response
                            producer.close()
                            consumer.close()
                            return@launch
                        }
                    }
                }
            }

            job.join()
        } finally {
            producer.close()
            consumer.close()
        }
    }

}