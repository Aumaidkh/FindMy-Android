package com.hopcape.findmy.feature_auth.domain.di

import com.hopcape.findmy.feature_auth.domain.repo.AuthRepository
import com.hopcape.findmy.feature_auth.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun providesLoginUseCase(
        repository: AuthRepository
    ) = LoginUseCase(repository)
}