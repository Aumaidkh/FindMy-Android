package com.hopcape.findmy.core.data.utils

import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.hopcape.findmy.core.domain.model.ErrorEntity
import com.hopcape.findmy.core.domain.utils.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler{

    override fun getError(throwable: Throwable?): ErrorEntity {
        return when(throwable){
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    // no cache found in case of no network, thrown by retrofit -> treated as network error
                    //DataConstants.Network.HttpStatusCode.UNSATISFIABLE_REQUEST -> ErrorEntity.Network

                    // not found
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound

                    // access denied
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied

                    // unavailable service
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable

                    // all the others will be treated as unknown error
                    else -> ErrorEntity.Unknown
                }
            }

            is FirebaseAuthInvalidCredentialsException -> ErrorEntity.IncorrectPassword

            is FirebaseAuthEmailException -> ErrorEntity.EmailAlreadyExists

            is FirebaseTooManyRequestsException -> ErrorEntity.TooManyAttempts

            else -> ErrorEntity.Unknown
        }
    }
}