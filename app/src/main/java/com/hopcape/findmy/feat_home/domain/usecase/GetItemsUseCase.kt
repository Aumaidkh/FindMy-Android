package com.hopcape.findmy.feat_home.domain.usecase

import com.hopcape.findmy.core.domain.model.ErrorEntity
import com.hopcape.findmy.core.domain.utils.ErrorHandler
import com.hopcape.findmy.core.utils.Result
import com.hopcape.findmy.core.utils.UiEvent
import com.hopcape.findmy.feat_home.domain.ReportedItem
import com.hopcape.findmy.feat_home.domain.repo.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository,
    private val errorHandler: ErrorHandler
) {

    fun execute() = flow<UiEvent<List<ReportedItem>>> {
        emit(UiEvent.Loading())
        repository.getLostItems().also {
            when(it){
                is Result.Error -> emit(UiEvent.Error(message = it.error))
                is Result.Success -> emit(UiEvent.Success(data = it.data))
            }
        }
    }.catch {
        emit(UiEvent.Error(errorHandler.getError(it)))
    }.flowOn(Dispatchers.IO)
}