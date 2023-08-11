package banquemisr.com.irrigationsystem.repositories

import banquemisr.com.irrigationsystem.model.Land
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LandRepository : MongoRepository<Land,String>