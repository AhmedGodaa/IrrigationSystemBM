package banquemisr.com.sensiorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ServicemenApplication

fun main(args: Array<String>) {
    runApplication<ServicemenApplication>(*args)
}
