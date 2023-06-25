package com.hopcape.findmy.feat_home.data.di

import com.hopcape.findmy.feat_home.data.repo.ItemRepositoryImpl
import com.hopcape.findmy.feat_home.domain.repo.ItemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface HomeDataLayerBinding {

    @Binds
    fun bindsItemRepository(impl: ItemRepositoryImpl): ItemRepository
}