package com.banquemisr.alertservice.config


import com.banquemisr.alertservice.exceptions.EntityNotFoundException
import com.banquemisr.alertservice.exceptions.UnauthorizedException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.stream.Collectors


@ControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<Message> {
        log.error("Exception occurred: ", ex)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Message(ex.message))
    }


    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException, request: WebRequest): ResponseEntity<Message> {
        log.error("EntityNotFoundException occurred: ", ex)

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Message(ex.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<Message> {
        log.error("MethodArgumentNotValidException occurred: ", ex)

        val bindingResult: BindingResult = ex.bindingResult
        val errorMessage: String = bindingResult.fieldErrors
            .stream()
            .map { error: FieldError -> "${error.field} ${error.defaultMessage}" }
            .collect(Collectors.joining(", "))

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Message(errorMessage))
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(ex: BindException, request: WebRequest): ResponseEntity<Message> {
        log.error("BindException occurred: ", ex)

        val errorMessage: String = ex.fieldErrors
            .stream()
            .map { error: FieldError -> "${error.field} ${error.defaultMessage}" }
            .collect(Collectors.joining(", "))

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Message(errorMessage))
    }


    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException, request: WebRequest): ResponseEntity<Message> {
        log.error("UnauthorizedException occurred: ", ex)

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Message(ex.message))
    }

    // Add more exception handlers as needed for your application

    data class Message(val message: String?)
}

