package banquemisr.com.sensiorservice.utils

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin.NewTopics

@Configuration
class KafkaProducerConfig {
    @Bean
    fun applicationKafkaTopics(): NewTopics {
        return NewTopics(
            NewTopic("alert-topic", 5, 1.toShort()),
            NewTopic("sensor-topic", 5, 1.toShort()),
            NewTopic("test", 5, 1.toShort())
        )

    }
}