package com.hopcape.findmy.feature_auth.di

import com.hopcape.findmy.feature_auth.data.repo.AuthRepositoryImpl
import com.hopcape.findmy.feature_auth.domain.repo.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
//object ViewModelModule {
//
//
//}

interface ViewModelModule {
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}