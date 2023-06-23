package com.hopcape.findmy.core.domain.utils

import com.hopcape.findmy.core.domain.model.ErrorEntity

/**
 * Converts a throwable into a user understandable
 * @see ErrorEntity*/
interface ErrorHandler {

    /**
     * Handles a
     * @param throwable
     * @return ErrorEntity out of it*/
    fun getError(throwable: Throwable?): ErrorEntity
}