package com.hopcape.findmy.core.domain.di

import com.hopcape.findmy.core.domain.usecases.validation.EmailValidator
import com.hopcape.findmy.core.domain.usecases.validation.FullNameValidator
import com.hopcape.findmy.core.domain.usecases.validation.PasswordValidator
import com.hopcape.findmy.core.domain.utils.ErrorHandler
import com.hopcape.findmy.core.domain.utils.SafeApiCallHandler
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

    /**
     * Providing Full Name Validator*/
    @Provides
    fun providesFullNameValidator() = FullNameValidator()

    @Provides
    fun safeApiHandler(errorHandler: ErrorHandler) = SafeApiCallHandler(errorHandler)
}