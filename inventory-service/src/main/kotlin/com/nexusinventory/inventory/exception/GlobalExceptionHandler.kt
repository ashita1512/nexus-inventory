package com.nexusinventory.inventory.exception

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(ex: DataIntegrityViolationException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.CONFLICT.value(),
            message = "A record with this value already exists. Please use a unique value."
        )
        // When a UNIQUE constraint (like on your SKU) is violated, Spring throws this specific exception.
        // We catch it and return a 409 Conflict status with our custom error message.
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.NOT_FOUND.value(),
            message = ex.message
        )
        // We can also handle the "not found" exception from our service layer.
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
}