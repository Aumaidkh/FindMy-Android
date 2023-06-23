package com.hopcape.findmy.feature_auth.login

import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feature_auth.domain.models.User

sealed class LoginViewState {
    object Loading: LoginViewState()
    object Initial: LoginViewState()
    data class Success(val user: User): LoginViewState()
    data class Error(val error: UiText): LoginViewState()
}

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val emailError: UiText? = null,
    val passwordError: UiText? = null
)