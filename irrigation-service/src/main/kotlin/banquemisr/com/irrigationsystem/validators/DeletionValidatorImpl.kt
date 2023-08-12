package banquemisr.com.irrigationsystem.validators

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
class DeletionValidatorImpl<T,ID : Any> : DeletionValidator<T, ID> {
    override fun validateDeletion(id: ID, repository: CrudRepository<T, ID>) {
        if (repository.existsById(id)) {
            throw DeletionException("Entity with id $id was not deleted")
        }
    }

    override fun validateExistence(id: ID, repository: CrudRepository<T, ID>) {
        if (!repository.existsById(id)) {
            throw DeletionException("Entity with id $id does not exist")
        }
    }
}