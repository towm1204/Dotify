package com.towm1204.dotify.models

data class User(
    val username: String,
    val firstName: String,
    val lastName: String,
    val hasNose: Boolean,
    val platForm: Double,
    val profilePicURL: String?
)