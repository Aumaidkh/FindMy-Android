package com.hopcape.findmy.core.data.di

import com.hopcape.findmy.core.data.utils.EncryptorImpl
import com.hopcape.findmy.core.data.utils.ErrorHandlerImpl
import com.hopcape.findmy.core.domain.utils.Encryptor
import com.hopcape.findmy.core.domain.utils.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {

    @Provides
    @Singleton
    fun providesErrorHandler(): ErrorHandler {
        return ErrorHandlerImpl()
    }

    @Singleton
    @Provides
    fun provideEncryptor(): Encryptor {
        return EncryptorImpl("FindMyKeyAlias")
    }
}