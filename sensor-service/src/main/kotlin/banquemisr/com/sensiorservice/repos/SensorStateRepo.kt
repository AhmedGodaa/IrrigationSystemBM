package banquemisr.com.sensiorservice.repos

import banquemisr.com.sensiorservice.model.SensorState
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SensorStateRepo : MongoRepository<SensorState, String>