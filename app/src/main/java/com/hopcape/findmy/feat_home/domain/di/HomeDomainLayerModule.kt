package com.hopcape.findmy.feat_home.domain.di

import com.hopcape.findmy.core.domain.utils.ErrorHandler
import com.hopcape.findmy.feat_home.domain.repo.ItemRepository
import com.hopcape.findmy.feat_home.domain.usecase.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeDomainLayerModule {
    @Provides
    fun providesGetItemUseCase(
        repository: ItemRepository,
        errorHandler: ErrorHandler
    ): GetItemsUseCase {
        return GetItemsUseCase(
            repository = repository,
            errorHandler = errorHandler
        )
    }
}