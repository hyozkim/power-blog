package com.strong.blog.powerblog.exception

class ResourceNotFoundException(message: String) : BlogException(errorType = ErrorType.RESOURCE_NOT_FOUND, message = message)
