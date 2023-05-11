package banquemisr.com.irrigationsystem.config

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class CorsConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry
                    .addMapping("/**")
                    .allowedMethods(CorsConfiguration.ALL)
                    .allowedHeaders(CorsConfiguration.ALL)
                    .allowedOriginPatterns(CorsConfiguration.ALL)
            }
        }
    }
}