package com.namseox.app_tcare.data.model

data class RegisterResponse(
    val timestamp: String,
    val message: String,
    val code: Int,
    val statusCode: Int,
    val result: RegisterResult
)

data class RegisterResult(
    val user: User,
    val accessToken: String,
    val refreshToken: String
)

data class User(
    val id: Int,
    val createdAt: String,
    val updatedAt: String,
    val email: String,
    val password: String,
    val fullName : String,
    val role: Int,
    val lang: Int,
    val latitude: Double,
    val longitude: Double,
    val address: String = "",
    val phoneNumber : String = ""
)

data class CallError(
    val success: Boolean,
    val message: String,
    val errors: String
)
data class ChangePass(
    val currentPassword: String,
    val newPassword: String,
    val confirmPassword: String
)