package com.strong.blog.powerblog.exception

import org.springframework.http.converter.HttpMessageNotReadableException

data class CommonErrorResponse(
    val errorType: ErrorType,
    val errorMessage: String = "",
    val userMessage: String = errorType.message,
    val stackTrace: String? = null
) {
    companion object {
        fun of(e: Exception): CommonErrorResponse {
            val errorType = when (e) {
                is BlogException -> e.errorType()
                is IllegalStateException -> ErrorType.ILLEGAL_STATEMENT
                is IllegalArgumentException -> ErrorType.ILLEGAL_ARGUMENT
                is HttpMessageNotReadableException -> ErrorType.REQUEST_PARSE_ERROR
                else -> ErrorType.UNKNOWN
            }
            val errorMessage = e.message ?: ""
            return CommonErrorResponse(errorType = errorType, errorMessage = errorMessage, stackTrace = e.stackTraceToString())
        }
    }
}
