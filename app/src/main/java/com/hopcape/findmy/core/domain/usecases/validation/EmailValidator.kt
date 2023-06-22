package com.hopcape.findmy.core.domain.usecases.validation

import com.hopcape.findmy.R
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.core.utils.ValidationResult

/**
 * Used to validate an email by making following checks
 * If email is empty
 * If email is invalid e.g not in form of abc@def.com
 * */
class EmailValidator {

    private val EMAIL_VALIDATION_REGEX = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")

    operator fun invoke(email: String): ValidationResult {
        return when{
            /**
             * Ensuring Email is Not Empty
             * */
            email.isEmpty() -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.email_cannot_be_empty
                    )
                )
            }
            /**
             * Ensuring Email Is Of Form abc@deg.com*/
            !email.matches(EMAIL_VALIDATION_REGEX) -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.invalid_email
                    )
                )
            }

            else -> ValidationResult(
                success = true
            )
        }
    }

}