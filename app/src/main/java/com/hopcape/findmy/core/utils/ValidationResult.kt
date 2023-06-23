package com.hopcape.findmy.core.utils

/**
 * Used for wrapping up the result coming from
 * a validation use case
 * @param success - if the result is valid
 * @param message - validating error
 * */
data class ValidationResult(
    val success: Boolean,
    val message: UiText? = null
)