package com.example.basicspring.error

import com.example.basicspring.utils.ApiUtils
import com.example.basicspring.utils.ErrorMessage
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorExceptionHandler {
    private val _log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(CustomException::class)
    fun customErrorHandler(e : CustomException): ResponseEntity<ApiUtils> =
        ResponseEntity(ErrorMessage.getErrorMessage(e.message ?: ""), e.status)
            .also {
                _log.error(e.message)
            }
    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class, MethodArgumentNotValidException::class)
    fun badRequestHandler(e: RuntimeException): ResponseEntity<ApiUtils> =
        ResponseEntity(ErrorMessage.getErrorMessage(e.message?:""), HttpStatus.BAD_REQUEST)
            .also {
                _log.error(e.message)
            }

    @ExceptionHandler(NoSuchElementException::class)
    fun notFoundHandler(e: RuntimeException): ResponseEntity<ApiUtils> =
        ResponseEntity(ErrorMessage.getErrorMessage(e.message ?: ""), HttpStatus.NOT_FOUND)
            .also {
                _log.error(e.message)
            }

    @ExceptionHandler(RuntimeException::class)
    fun error5xxHandler(e: RuntimeException): ResponseEntity<ApiUtils> =
        ResponseEntity(ErrorMessage.getErrorMessage(e.message ?: ""), HttpStatus.INTERNAL_SERVER_ERROR)
            .also {
                _log.error(e.message)
            }
}