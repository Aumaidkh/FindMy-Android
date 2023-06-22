package com.hopcape.findmy.core.domain.di

import com.hopcape.findmy.core.domain.usecases.validation.EmailValidator
import com.hopcape.findmy.core.domain.usecases.validation.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    /**
     * Providing Validation Use Cases Here*/
    @Provides
    fun providesEmailValidator() = EmailValidator()

    /**
     * Providing Password Validator*/
    @Provides
    fun providesPasswordValidator() = PasswordValidator()
}