package banquemisr.com.irrigationsystem

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IrrigationSystemApplicationTests

fun Any.toJson(): String = jacksonObjectMapper().writeValueAsString(this)
