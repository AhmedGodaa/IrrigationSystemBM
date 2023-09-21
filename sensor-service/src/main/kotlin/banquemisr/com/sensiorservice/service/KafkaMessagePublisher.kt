package banquemisr.com.sensiorservice.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class KafkaMessagePublisher(
    private val template: KafkaTemplate<String, String>
) {
    private val logger = LoggerFactory.getLogger(KafkaMessagePublisher::class.java)

    fun sendMessageToTopic(message: String, topic: String) {
        val sender: CompletableFuture<SendResult<String, String>> = template.send(topic, message);
        sender.whenComplete { result, error ->
            if (error != null) {
                logger.error("Error while sending message to topic: $topic", error)
            } else {
                logger.info("Message sent to topic: $topic with offset: ${result?.recordMetadata?.offset()}")
            }
        }

    }

}