package banquemisr.com.sensiorservice.utils

import banquemisr.com.sensiorservice.utils.Constants.BASE_URL
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.client.RootUriTemplateHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriTemplateHandler
import java.time.Duration


@Component
class RestTemplateConfig {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate? {
        val uriTemplateHandler: UriTemplateHandler = RootUriTemplateHandler(BASE_URL)
        return builder
            .uriTemplateHandler(uriTemplateHandler)
            .setReadTimeout(Duration.ofMillis(1000))
            .build()
    }


}