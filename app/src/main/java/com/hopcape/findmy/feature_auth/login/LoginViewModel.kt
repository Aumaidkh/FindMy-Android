package com.hopcape.findmy.feature_auth.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.findmy.core.domain.usecases.validation.EmailValidator
import com.hopcape.findmy.core.domain.usecases.validation.PasswordValidator
import com.hopcape.findmy.core.utils.UiEvent
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feature_auth.domain.models.User
import com.hopcape.findmy.feature_auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val loginUseCase: LoginUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
): ViewModel() {

    private val _state = MutableStateFlow<LoginViewState>(LoginViewState.Initial)
    val state get() = _state.asStateFlow()

    private val _formState = MutableStateFlow(LoginFormState())
    val formState get() = _formState.asStateFlow()

    private val eventChannel = Channel<UiEvents>()
    val eventFlow get() = eventChannel.receiveAsFlow()

    fun onGoogleSignIn(){
        viewModelScope.launch {
            val intentSender = googleAuthUiClient.signIn()
            eventChannel.send(
                UiEvents.SignInWithGoogle(
                    intentSender = intentSender
                )
            )
        }
    }

    fun onEmailChange(newValue: String){
        _formState.update {
            it.copy(
                email = newValue,
                emailError = null
            )
        }

    }

    fun onPasswordChange(newValue: String){
        _formState.update {
            it.copy(
                password = newValue,
                passwordError = null
            )
        }
    }

    fun onSignInIntent(intent: Intent?) {
        viewModelScope.launch {
            val result = googleAuthUiClient.signInWithIntent(intent?:return@launch)
            if (result.data != null){
                _state.value = LoginViewState.Success(
                    user = result.data.run {
                        User(
                            name = username ?: "Anonymous",
                            email = userId,
                            profilePic = profilePictureUrl,
                            accessToken = "",
                            userId = userId
                        )
                    }
                )
                return@launch
            }

            _state.value = LoginViewState.Error(
                error = result.errorMessage.run {
                    UiText.Dynamic(this ?: "Something went wrong")
                }
            )

        }
    }

    fun login(){
        viewModelScope.launch {
            val email = formState.value.email
            val password = formState.value.password

            val emailValidationResult = emailValidator.invoke(email)
            val passwordValidator = passwordValidator.invoke(password)

            val hasError = listOf(
                emailValidationResult,
                passwordValidator
            ).any {
                !it.success
            }
            if (hasError){
                _formState.update {
                    it.copy(
                        emailError = emailValidationResult.message,
                        passwordError = passwordValidator.message
                    )
                }
                return@launch
            }
            loginUseCase(email,password).onEach { emission ->
                when(emission){
                    is UiEvent.Error -> {
                        _state.value = LoginViewState.Error(UiText.Dynamic(emission.message?.error?:"Something went wrong"))
                    }
                    is UiEvent.Loading -> {
                        _state.value = LoginViewState.Loading
                    }
                    is UiEvent.Success -> {
                        _state.value = LoginViewState.Success(emission.data!!)
                    }
                }
            }.launchIn(this)
        }
    }


}