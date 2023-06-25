package com.hopcape.findmy.feat_home.presentation.home.homescreen

import com.google.firebase.firestore.auth.User
import com.hopcape.findmy.core.presentation.utils.ScreenViewState
import com.hopcape.findmy.core.utils.UiText
import com.hopcape.findmy.feat_home.domain.ReportedItem


data class HomeScreenState(
    val lostItems: List<ReportedItem> = emptyList(),
    val foundItems: List<ReportedItem> = emptyList(),
    val itemsNearYou: List<ReportedItem> = emptyList(),
    val user: User? = null,
)

sealed interface HomeScreenViewState{
    object Loading: HomeScreenViewState
    object Initial: HomeScreenViewState
    class Success(val data: List<ReportedItem>): HomeScreenViewState
    class Error(val error: UiText): HomeScreenViewState
}