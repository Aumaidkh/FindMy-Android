package com.hopcape.findmy.core.domain.model


/**
 * Wrapper around all the type of errors that can occur*/
sealed class  ErrorEntity(val error: String){

    object Network : ErrorEntity("Network Error")

    object NotFound : ErrorEntity("Resource not found")

    object AccessDenied : ErrorEntity("Access Denied")

    object ServiceUnavailable : ErrorEntity("Service Unavailable")

    object Unknown : ErrorEntity("Unknown Error")

    object EmailAlreadyExists: ErrorEntity("Email already exists")

    object IncorrectPassword: ErrorEntity("Incorrect password")

    object TooManyAttempts: ErrorEntity("Too many attempts, please try again later")
}