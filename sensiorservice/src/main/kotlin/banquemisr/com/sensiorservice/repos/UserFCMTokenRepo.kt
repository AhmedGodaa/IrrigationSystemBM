package banquemisr.com.sensiorservice.repos

import banquemisr.com.sensiorservice.model.UserFCMToken
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFCMTokenRepo : MongoRepository<UserFCMToken, String>