package com.hopcape.findmy.feature_auth.domain.usecase

import com.hopcape.findmy.core.utils.Resource
import com.hopcape.findmy.core.utils.UiEvent
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feature_auth.domain.models.User
import com.hopcape.findmy.feature_auth.domain.repo.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Logs a user with email and password in and emits a flow of UiEvents
 * whether the login was in successful, unsuccessful or loading state*/
class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    operator fun invoke(email: String,password: String) = flow<UiEvent<User>> {
        emit(UiEvent.Loading())
        repository.login(email,password).also {
            when(it){
                is Resource.Error -> emit(UiEvent.Error(UiText.Dynamic(it.message)))
                is Resource.Loading -> emit(UiEvent.Loading())
                is Resource.Success -> emit(UiEvent.Success(it.data.run { User(
                    name = fullname ?: "Anonymous",
                    email = email,
                    profilePic = profilePic,
                    accessToken = ""
                ) }))
            }
        }
    }.catch {
        emit(UiEvent.Error(UiText.Dynamic(it.message.toString())))
    }.flowOn(Dispatchers.IO)
}