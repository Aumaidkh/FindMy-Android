package com.hopcape.findmy.feature_auth.domain.usecase

import android.util.Log
import com.hopcape.findmy.core.domain.utils.Encryptor
import com.hopcape.findmy.core.domain.utils.ErrorHandler
import com.hopcape.findmy.core.utils.Resource
import com.hopcape.findmy.core.utils.Result
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
 * whether the login was in successful, unsuccessful or loading state
 * @param repository for making api calls
 * @param errorHandler for handing errors
 * @param encryptor for encrypting password*/
class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val errorHandler: ErrorHandler,
    private val encryptor: Encryptor
) {

    operator fun invoke(email: String,password: String) = flow<UiEvent<User>> {
        emit(UiEvent.Loading())
        repository.login(email,encryptor.encrypt(password)).also {
            when(it){
                is Result.Error -> {
                    Log.d(TAG, "invoke: Error")
                    emit(UiEvent.Error(it.error))
                }
                is Result.Success -> {
                    Log.d(TAG, "invoke: Success")
                    emit(UiEvent.Success(data = it.data))
                }
            }
        }
    }.catch {
        Log.d(TAG, "invoke: Exception: $it")
        emit(UiEvent.Error(errorHandler.getError(it)))
    }.flowOn(Dispatchers.IO)
}

private const val TAG = "LoginUseCase"