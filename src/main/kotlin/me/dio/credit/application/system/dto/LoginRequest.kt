package me.dio.credit.application.system.dto

data class LoginRequest(
    val email: String,
    val password: String
)