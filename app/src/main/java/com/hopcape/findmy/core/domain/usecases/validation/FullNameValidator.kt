package com.hopcape.findmy.core.domain.usecases.validation


import com.hopcape.findmy.R
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.core.utils.ValidationResult

/**
 * Used to validate full name by making following checks
 * If name is  empty
 * If name is invalid e.g contains digits or special symbols other than .
 * */
class FullNameValidator {

    private val NAME_VALIDATION_REGEX = Regex("^[A-Za-z.]+(?:\\\\s+[A-Za-z.]+)*\$")

    operator fun invoke(fullName: String): ValidationResult {
        return when{
            /**
             * Ensuring Email is Not Empty
             * */
            fullName.isEmpty() -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.name_cannot_be_empty
                    )
                )
            }
            /**
             * Ensuring Email Is Of Form abc@deg.com*/
            !fullName.matches(NAME_VALIDATION_REGEX) -> {
                ValidationResult(
                    success = false,
                    message = UiText.StringResource(
                        resId = R.string.invalid_name
                    )
                )
            }

            else -> ValidationResult(
                success = true
            )
        }
    }

}