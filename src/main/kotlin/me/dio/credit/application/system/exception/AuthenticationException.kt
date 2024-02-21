package me.dio.credit.application.system.exception

data class AuthenticationException(override val message: String, val errorCode: String) : Exception()
