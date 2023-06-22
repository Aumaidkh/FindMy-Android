package com.hopcape.findmy.core.domain.usecases.validation

import com.hopcape.findmy.R
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.core.utils.ValidationResult


/**
 * Validates the password by making the following checks
 * Checking Password Length
 * Checking Password Empty
 * Checking Password Chars
 * */
class PasswordValidator {

    private val PASSWORD_LENGTH = 6
    private val PASSWORD_VALIDATION_REGEX = Regex("^(?=.*[a-z])(?=.*\\d)(?=.*[#\$@!%&*?])[A-Za-z\\d#\$@!%&*?]{6,30}\$")

    operator fun invoke(password: String): ValidationResult {
        return when{
            /**
             * Ensuring password is Not Empty
             * */
            password.isEmpty() -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.password_cannot_be_empty
                    )
                )
            }

            /**
             * Ensuring password is min 6 chars long*/
            password.length < PASSWORD_LENGTH -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.password_size_error,
                        args = arrayOf(PASSWORD_LENGTH)
                    )
                )
            }

            /**
             * Ensuring Password contains a special symbol and a digit*/
            !password.matches(PASSWORD_VALIDATION_REGEX) -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.password_validation_error
                    )
                )
            }

            else -> ValidationResult(
                success = true
            )
        }
    }

}