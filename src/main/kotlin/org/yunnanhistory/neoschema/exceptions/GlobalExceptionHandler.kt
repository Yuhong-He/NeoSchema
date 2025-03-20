package org.yunnanhistory.neoschema.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

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