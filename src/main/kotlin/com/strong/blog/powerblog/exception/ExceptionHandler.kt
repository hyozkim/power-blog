package com.strong.blog.powerblog.exception

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(BlogException::class)
    fun handleException(e: BlogException): ResponseEntity<CommonErrorResponse> {
        log.error(e) { "Message Center API exception. message=[${e.message}]" }
        return ResponseEntity(CommonErrorResponse.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleException(e: ResourceNotFoundException): ResponseEntity<CommonErrorResponse> {
        log.error(e) { "Resource not found. message=[${e.message}]" }
        return ResponseEntity(CommonErrorResponse.of(e), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleException(e: IllegalStateException): ResponseEntity<CommonErrorResponse> {
        log.error(e) { "Unsupported status update requested. message=[${e.message}]" }
        return ResponseEntity(CommonErrorResponse.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleException(e: IllegalArgumentException): ResponseEntity<CommonErrorResponse> {
        log.error(e) { "Illegal argument get. message=[${e.message}]" }
        return ResponseEntity(CommonErrorResponse.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleException(e: HttpMessageNotReadableException): ResponseEntity<CommonErrorResponse> {
        log.error(e) { "Http request parse error. message=[${e.message}]" }
        return ResponseEntity(CommonErrorResponse.of(e), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<CommonErrorResponse> {
        log.error(e) { "Unknown error occurred. message=[${e.message}]" }
        return ResponseEntity(CommonErrorResponse.of(e), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    companion object {
        private val log = KotlinLogging.logger { }
    }
}
