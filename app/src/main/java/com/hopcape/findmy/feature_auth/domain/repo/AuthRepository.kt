package com.hopcape.findmy.feature_auth.domain.repo

import com.hopcape.findmy.core.utils.Resource
import com.hopcape.findmy.feature_auth.data.dto.UserDto

/**
 * This repository exposes necessary apis to be used
 * for logging a user in, verifying his phone number, updating his password or registering
 * a new user etc.*/
interface AuthRepository {

    /**
     * Logs a user in with
     * @param email
     * @param password
     * */
    suspend fun login(email: String,password: String) : Resource<UserDto>


    /**
     * Registers a new user with
     * @param email
     * @param password
     * @param fullname
     * @param phone
     * */
    suspend fun register(email: String, password: String, fullname: String, phone: String) : Resource<UserDto>

    /**
     * Requests an otp to reset password for an account
     * @param email*/
    suspend fun requestOtpOnEmail(email: String)


    /**
     * Request an otp to the phone for resetting the password
     * @param phone*/
    suspend fun requestOtpOnPhone(phone: String)


    /**
     * Verifies the
     * @param otp which was sent to user
     * @param email
     * @param phone*/
    suspend fun verifyOtp(otp: String,email: String,phone: String)

    /**
     * Updates the password for the user with
     * @param email
     * @param newPassword
     * */
    suspend fun updatePassword(email: String, newPassword: String)
}