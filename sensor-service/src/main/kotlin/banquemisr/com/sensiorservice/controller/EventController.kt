package banquemisr.com.sensiorservice.controller

import banquemisr.com.sensiorservice.service.KafkaMessagePublisher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/event")
class EventController(
    private val kafkaMessagePublisher: KafkaMessagePublisher
) {

    @GetMapping("/send/{message}")
    fun sendEvent(@PathVariable message: String) {
        kafkaMessagePublisher.sendMessageToTopic(message, "test")
    }
}