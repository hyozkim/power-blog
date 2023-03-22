package com.strong.blog.powerblog.exception

open class BlogException : RuntimeException {
    private val errorType: ErrorType

    constructor(message: String) : super(message) {
        this.errorType = ErrorType.UNKNOWN
    }

    constructor(message: String, cause: Throwable) : super(message, cause) {
        this.errorType = ErrorType.UNKNOWN
    }

    constructor(errorType: ErrorType, cause: Throwable) : super(cause) {
        this.errorType = errorType
    }

    constructor(errorType: ErrorType, message: String) : super(message) {
        this.errorType = errorType
    }

    constructor(errorType: ErrorType, message: String, cause: Throwable) : super(message, cause) {
        this.errorType = errorType
    }

    fun errorType(): ErrorType = errorType
}
