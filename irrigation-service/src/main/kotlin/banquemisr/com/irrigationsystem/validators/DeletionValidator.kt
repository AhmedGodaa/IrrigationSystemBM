package banquemisr.com.irrigationsystem.validators

import org.springframework.data.repository.CrudRepository

interface DeletionValidator<T,ID> {
    fun validateDeletion(id: ID, repository: CrudRepository<T, ID>)
    fun validateExistence(id: ID, repository: CrudRepository<T, ID>)

}
