package com.hopcape.findmy.feat_home.presentation.home.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.findmy.core.utils.UiEvent
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feat_home.domain.ItemType
import com.hopcape.findmy.feat_home.domain.repo.ItemRepository
import com.hopcape.findmy.feat_home.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
): ViewModel() {

    private val _state = MutableStateFlow<HomeScreenViewState>(HomeScreenViewState.Initial)
    val state get() = _state.asStateFlow()

    init {
        getItemsUseCase.execute().onEach { emission ->
            when(emission){
                is UiEvent.Error -> _state.value = HomeScreenViewState.Error(error = UiText.Dynamic(emission.message?.error ?: "Something went wrong"))
                is UiEvent.Loading -> _state.value = HomeScreenViewState.Loading
                is UiEvent.Success -> {
                    val homeScreenState = HomeScreenState(
                        lostItems = emission.data?.filter { it.itemType == ItemType.LOST } ?: emptyList(),
                        foundItems = emission.data?.filter { it.itemType == ItemType.FOUND } ?: emptyList(),
                        itemsNearYou = emptyList(),
                        user = null
                    )
                    _state.value = HomeScreenViewState.Success(homeScreenState)
                }
            }
        }.launchIn(viewModelScope)
    }
}

private const val TAG = "HomeScreenViewModel"