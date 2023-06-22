package com.hopcape.findmy.feature_auth.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.hopcape.findmy.core.utils.Resource
import com.hopcape.findmy.core.utils.safeApiCall
import com.hopcape.findmy.feature_auth.data.dto.UserDto
import com.hopcape.findmy.feature_auth.domain.repo.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthRepository {

    override suspend fun login(email: String, password: String): Resource<UserDto> {
        return safeApiCall {
            firebaseAuth.signInWithEmailAndPassword(email, password).await().run {
                UserDto(
                    userId = user?.uid,
                    fullname = user?.displayName,
                    email = user?.email,
                    profilePic = user?.displayName,
                    phone = user?.phoneNumber
                )
            }
        }
    }

    override suspend fun register(email: String, password: String, fullname: String, phone: String): Resource<UserDto> {
        // Create User with email password
        val userResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

        // TODO: Save User to firebase Firestore

        return Resource.success(
            data = userResult.user.run {
                UserDto(
                    userId = this?.uid,
                    fullname = this?.displayName,
                    email = this?.email,
                    profilePic = this?.displayName,
                    phone = this?.phoneNumber
                )
            }
        )
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