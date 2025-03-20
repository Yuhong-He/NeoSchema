package org.yunnanhistory.neoschema.exceptions

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleNotFoundException(ex: EmptyResultDataAccessException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message ?: "Resource not found")
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            "Invalid argument type: '${ex.name}', should be type: ${ex.requiredType}"
        )
    }

    @ExceptionHandler(ClientErrorException::class)
    fun handleClientErrorException(ex: ClientErrorException): ResponseEntity<String> {
        return ResponseEntity.status(ex.errorCode).body(
            ex.message ?: "HTTP Client Error"
        )
    }

//    @ExceptionHandler(Exception::class)
//    fun handleGenericException(ex: Exception): ResponseEntity<String> {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error")
//    }
}