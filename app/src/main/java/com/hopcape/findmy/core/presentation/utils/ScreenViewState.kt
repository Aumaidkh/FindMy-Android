package com.hopcape.findmy.core.presentation.utils

import com.hopcape.findmy.core.utils.UiText

open class ScreenViewState<T>{
    companion object {
        object Loading: ScreenViewState<Nothing>()
        object Initial: ScreenViewState<Nothing>()
        class Success<T>(val data: T): ScreenViewState<T>()
        class Error(val error: UiText): ScreenViewState<Nothing>()
    }
}
