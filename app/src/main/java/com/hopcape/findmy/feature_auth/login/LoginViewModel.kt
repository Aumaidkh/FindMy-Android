package com.hopcape.findmy.feature_auth.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feature_auth.domain.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient
): ViewModel() {

    private val _state = MutableStateFlow<LoginViewState>(LoginViewState.Initial)
    val state get() = _state.asStateFlow()

    private val formState = MutableStateFlow(LoginFormState())

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
        formState.update {
            it.copy(
                email = newValue
            )
        }
    }

    fun onPasswordChange(newValue: String){
        formState.update {
            it.copy(
                password = newValue
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
                            accessToken = ""
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

}