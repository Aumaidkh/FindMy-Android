package com.hopcape.findmy.feature_auth.domain.models

data class User(
    val name: String,
    val email: String?,
    val profilePic: String?,
    val accessToken: String
)

