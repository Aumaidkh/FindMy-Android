package com.hopcape.findmy.core.utils

import com.hopcape.findmy.core.domain.model.ErrorEntity

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
    val message: ErrorEntity? = null
) {

    class Loading<T>(): UiEvent<T>()

    class Success<T>(data:T): UiEvent<T>(data= data)

    class Error<T>(message:ErrorEntity): UiEvent<T>(message= message)
}