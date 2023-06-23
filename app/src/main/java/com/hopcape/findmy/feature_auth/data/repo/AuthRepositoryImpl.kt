package com.hopcape.findmy.feature_auth.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.hopcape.findmy.core.domain.utils.SafeApiCallHandler
import com.hopcape.findmy.core.utils.Result
import com.hopcape.findmy.feature_auth.domain.models.User
import com.hopcape.findmy.feature_auth.domain.repo.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val safeApiCallHandler: SafeApiCallHandler
): AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return safeApiCallHandler {
            firebaseAuth.signInWithEmailAndPassword(email, password).await().run {
                User(
                    userId = user?.uid,
                    name = user?.displayName,
                    email = user?.email,
                    profilePic = user?.displayName,
                    accessToken = ""
                )
            }
        }
    }

    override suspend fun register(email: String, password: String, fullname: String, phone: String): Result<User> {
        return safeApiCallHandler{ // TODO: Save User To Firebase Firestore
            firebaseAuth.createUserWithEmailAndPassword(email,password).await().run {
                User(
                    userId = user?.uid,
                    name = fullname,
                    email = email,
                    profilePic = null,
                    accessToken = ""
                )
            }
        }
    }

    override suspend fun requestOtpOnEmail(email: String) {

    }

    override suspend fun requestOtpOnPhone(phone: String) {

    }

    override suspend fun verifyOtp(otp: String, email: String, phone: String) {

    }

    override suspend fun updatePassword(email: String, newPassword: String) {

    }
}