package banquemisr.com.irrigationsystem.config

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaMessageListener {
    private val logger = LoggerFactory.getLogger(KafkaMessageListener::class.java)

    @KafkaListener(topics = ["test"], groupId = "group_id")
    fun consumeMessage(message: String) {
        logger.info("Message received: $message")
    }
}