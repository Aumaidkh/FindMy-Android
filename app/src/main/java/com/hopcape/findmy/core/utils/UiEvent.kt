package com.hopcape.findmy.core.utils

/**
 * Wrapper around the events that are to
 * be emitted from the use case layer
 * @param data - The data that is to be emitted when
 * Success Event
 * @param message - The message to be shown when event
 * to be emitted is error
 * */
sealed class UiEvent<T>(
    val data: T? = null,
    val message: UiText? = null
) {

    class Loading<T>(): UiEvent<T>()

    class Success<T>(data:T): UiEvent<T>(data= data)

    class Error<T>(message:UiText): UiEvent<T>(message= message)
}