package com.hopcape.findmy.core.utils

import com.hopcape.findmy.core.domain.model.ErrorEntity

/**
 * Wrapper around the data of generic type which lets us decide whether
 * the result is an error or a success result*/
sealed class Result<T> {

    /**
     * Success Result
     * @param data  contains the data*/
    data class Success<T>(val data: T) : Result<T>()

    /**
     * Returned in case of an error
     * @param error contains an error*/
    data class Error<T>(val error: ErrorEntity) : Result<T>()
}