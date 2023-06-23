package com.hopcape.findmy.core.domain.usecases.validation

import com.hopcape.findmy.R
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.core.utils.ValidationResult


/**
 * Used to validate a phone number by making following checks
 * If phone number is empty
 * If phone number is invalid e.g contains alphabets or symbols
 * */
class PhoneNumberValidator {

    private val PHONE_VALIDATION_REGEX = Regex("^\\+?\\d{1,3}[-.\\s]?\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}\$")

    operator fun invoke(phone: String): ValidationResult {
        return when{
            /**
             * Empty Phone Number is also accepted
             * */
            phone.isEmpty() -> {
                ValidationResult(
                    success = true
                )
            }
            /**
             * Ensuring Phone Number is valid considering valid only when it contains, digits only and equal to 10
             * */
            !phone.matches(PHONE_VALIDATION_REGEX) -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.invalid_phone_number
                    )
                )
            }

            else -> ValidationResult(
                success = true
            )
        }
    }

}