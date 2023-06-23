package com.hopcape.findmy.feature_auth.presentation.sign_up

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.findmy.core.domain.usecases.validation.EmailValidator
import com.hopcape.findmy.core.domain.usecases.validation.FullNameValidator
import com.hopcape.findmy.core.domain.usecases.validation.PasswordValidator
import com.hopcape.findmy.core.utils.UiEvent
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feature_auth.domain.models.User
import com.hopcape.findmy.feature_auth.domain.usecase.RegisterUseCase
import com.hopcape.findmy.feature_auth.presentation.login.GoogleAuthUiClient
import com.hopcape.findmy.feature_auth.presentation.login.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * State Holder for holding signup state and
 * exposes api for signing up a user
 * */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient,
    private val registerUseCase: RegisterUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val fullNameValidator: FullNameValidator
): ViewModel() {

    private val _state = MutableStateFlow<SignUpViewState>(SignUpViewState.Initial)
    val state get() = _state.asStateFlow()

    private val _formState = MutableStateFlow(SignUpFormState())
    val formState = _formState.asStateFlow()

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

    fun onSignInIntent(intent: Intent?) {
        viewModelScope.launch {
            val result = googleAuthUiClient.signInWithIntent(intent?:return@launch)
            if (result.data != null){
                _state.value = SignUpViewState.Success(
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

            _state.value = SignUpViewState.Error(
                error = result.errorMessage.run {
                    UiText.Dynamic(this ?: "Something went wrong")
                }
            )

        }
    }

    fun onFullNameChange(newValue: String){
        _formState.update {
            it.copy(
                fullNameError = null,
                fullName = newValue
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

    fun onRegister(){
        Log.d(TAG, "onRegister: ")
        val email = _formState.value.email
        val fullname = _formState.value.fullName
        val password = _formState.value.password

        val emailResult = emailValidator.invoke(email)
        val passwordResult = passwordValidator.invoke(password)
        val fullNameResult = fullNameValidator.invoke(fullname)

        val hasError = listOf(
            emailResult,
            passwordResult,
            fullNameResult
        ).any{
            !it.success
        }

        if (hasError){
            _formState.update {
                it.copy(
                    fullNameError = fullNameResult.message,
                    emailError = emailResult.message,
                    passwordError = passwordResult.message
                )
            }
            return
        }

        viewModelScope.launch {
            registerUseCase(
                email = email,
                password = password,
                fullname = fullname
            ).onEach { emission ->
                when(emission){
                    is UiEvent.Error -> {
                        Log.d(TAG, "onRegister: Error")
                        _state.value = SignUpViewState.Error(UiText.Dynamic(emission.message?.error ?: "Something went wrong"))
                    }
                    is UiEvent.Loading -> _state.value = SignUpViewState.Loading
                    is UiEvent.Success -> {
                        Log.d(TAG, "onRegister: Success")
                        _state.value = SignUpViewState.Success(user = emission.data!!)
                    }
                }
            }.launchIn(this)
        }
    }
}

private const val TAG = "SignUpViewModel"