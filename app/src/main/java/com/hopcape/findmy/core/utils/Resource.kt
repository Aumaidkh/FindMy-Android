package com.hopcape.findmy.core.utils

/**
 * Class for wrapping the api response*/
sealed class Resource<out T> {
    /**
     * Api Call Succeeded
     * @param data */
    data class Success<out T>(val data: T) : Resource<T>()

    /**
     * Api Call Failed
     * @param message error message*/
    data class Error(val message: String) : Resource<Nothing>()

    /**
     * Api Call Under Way*/
    object Loading : Resource<Nothing>()

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun error(message: String): Resource<Nothing> = Error(message)
        fun loading(): Resource<Nothing> = Loading
    }
}
