package com.hopcape.findmy.core.utils

import kotlinx.coroutines.CancellationException

suspend fun <T> safeApiCall(apiCall:suspend () -> T): Resource<T> {
    return try {
        val result = apiCall.invoke()
        Resource.success(data = result)
    } catch (e: Exception){
        if (e is CancellationException) throw e
        Resource.error(e.message.toString())
    }
}