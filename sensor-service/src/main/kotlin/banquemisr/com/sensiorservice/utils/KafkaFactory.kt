package banquemisr.com.sensiorservice.utils

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.stereotype.Service
import java.util.*

@Service
class KafkaFactory(
    @Value("spring.kafka.bootstrap-servers") private val bootstrapServers: String
) {

    fun <K, V> createProducer(): KafkaProducer<K, V> {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java.name
        return KafkaProducer(props)
    }

    fun <K, V> createConsumer(
        groupId: String,
        topic: String,
    ): KafkaConsumer<K, V> {
        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java.name
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        val consumer = KafkaConsumer<K, V>(props)
        consumer.subscribe(listOf(topic))
        return consumer
    }
}
