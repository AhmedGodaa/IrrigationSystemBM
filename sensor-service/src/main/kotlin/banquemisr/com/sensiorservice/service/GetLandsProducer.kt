package banquemisr.com.sensiorservice.service

import banquemisr.com.sensiorservice.events.GetAllLandsEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class GetLandsProducer(
    private val kafkaTemplate: KafkaTemplate<String, GetAllLandsEvent>
) {
    private val logger = LoggerFactory.getLogger(GetLandsProducer::class.java)

    fun sendGetLandsEvent(getAllLandsEvent: GetAllLandsEvent) {
        val message = MessageBuilder.withPayload(getAllLandsEvent)
            .setHeader(KafkaHeaders.TOPIC, "getLands")
            .build();
        val send = kafkaTemplate.send(message)
        send.whenComplete { it, ex ->
            if (ex != null) {
                logger.error("Unable to send message=[${getAllLandsEvent}] due to : ${ex.message}")
            } else {
                logger.info("Sent message=[${getAllLandsEvent}] with offset=[${it.recordMetadata.offset()}]")
            }
        }
    }
}