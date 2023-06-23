package com.hopcape.findmy.core.domain.utils

import javax.inject.Inject
import com.hopcape.findmy.core.utils.Result
import kotlinx.coroutines.CancellationException

/**
 * Calls Network calls safely
 * @param errorHandler for getting errors into ErrorEntity*/
class SafeApiCallHandler @Inject constructor(
    private val errorHandler: ErrorHandler
) {

    suspend operator fun <T> invoke(block: suspend () -> T): Result<T>{
        return try {
            Result.Success(data = block.invoke())
        } catch (e: Exception){
            if (e is CancellationException) throw e
            Result.Error(error = errorHandler.getError(e))
        }
    }
}