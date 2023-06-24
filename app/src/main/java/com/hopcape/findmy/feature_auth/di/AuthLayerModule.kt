package com.hopcape.findmy.feature_auth.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.hopcape.findmy.feature_auth.presentation.login.GoogleAuthUiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Providing Datalayer dependencies specific to this layer
 * Which may be providing API's*/
@Module
@InstallIn(SingletonComponent::class)
object AuthLayerModule {

    @Provides
    fun providesGoogleAuthUiClient(
        @ApplicationContext context: Context
    ): GoogleAuthUiClient {
        FirebaseApp.initializeApp(context)
        return GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }


    /**
     * Firebase Auth*/
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    /**
     * Provide Google Sign In Options*/
    @Provides
    fun provideGoogleSignInOptions() =
        GoogleSignInOptions.Builder()
            .requestIdToken("11707425813-9qm8diga4plovu99k1sf7ic8h4830djf.apps.googleusercontent.com")
            .requestEmail()
            .requestProfile()
            .build();


    /**
     * Google Sign in Client*/
    fun provideGoogleSignClient(
        @ApplicationContext context: Context,
        googleSignInOptions: GoogleSignInOptions
    ) =
        GoogleSignIn.getClient(context,googleSignInOptions)




}