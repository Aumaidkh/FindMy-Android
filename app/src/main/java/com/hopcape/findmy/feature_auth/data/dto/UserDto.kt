package com.hopcape.findmy.feature_auth.data.dto

data class UserDto(
    val userId: String? = null,
    val fullname: String? = null,
    val email: String? = null,
    val profilePic: String? = null,
    val phone: String? = null
)
