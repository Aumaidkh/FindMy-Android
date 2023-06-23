package com.hopcape.findmy.feature_auth.domain.usecase


import android.util.Log
import com.hopcape.findmy.core.domain.utils.Encryptor
import com.hopcape.findmy.core.domain.utils.ErrorHandler
import com.hopcape.findmy.core.utils.Result
import com.hopcape.findmy.core.utils.UiEvent
import com.hopcape.findmy.feature_auth.domain.models.User
import com.hopcape.findmy.feature_auth.domain.repo.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


/**
 * This use case is responsible for register a user
 * to the cloud or the backend server
 * @param repository For making calls to register a user
 * @param errorHandler For handling errors
 * @param encryptor For encrypting password before sending it
 * */
class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val encryptor: Encryptor,
    private val errorHandler: ErrorHandler
) {

    /**
     * When Invoked with following args creates a new user or registers a new user
     * @param email
     * @param password
     * @param fullname
     * */
    operator fun invoke(email: String,password: String,fullname: String) = flow<UiEvent<User>> {
        emit(UiEvent.Loading())
        repository.register(
            email = email,
            password = encryptor.encrypt(password),
            fullname = fullname,
            phone = ""
        ).also {
            when(it){
                is Result.Error -> {
                    Log.d(TAG, "invoke: Error")
                    emit(UiEvent.Error(it.error))
                }
                is Result.Success -> {
                    Log.d(TAG, "invoke: Success")
                    emit(UiEvent.Success(it.data))
                }
            }
        }
    }.catch {
        Log.d(TAG, "invoke: Exception: ${it.message}")
        emit(UiEvent.Error(errorHandler.getError(it)))
    }.flowOn(Dispatchers.IO)

}

private const val TAG = "RegisterUseCase"