package com.hopcape.findmy.feature_auth.presentation.sign_up

import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feature_auth.domain.models.User

sealed class SignUpViewState {
    object Loading: SignUpViewState()
    object Initial: SignUpViewState()
    data class Success(val user: User): SignUpViewState()
    data class Error(val error: UiText): SignUpViewState()
}

data class SignUpFormState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val fullNameError: UiText? = null,
    val emailError: UiText? = null,
    val passwordError: UiText? = null
)