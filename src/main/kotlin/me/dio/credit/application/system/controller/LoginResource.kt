package me.dio.credit.application.system.controller

import me.dio.credit.application.system.dto.LoginDto
import me.dio.credit.application.system.service.impl.UserDetailsCustomService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginResource(
    private val userDetailsCustomService: UserDetailsCustomService
) {
    @PostMapping
    fun login(@RequestBody login: LoginDto): ResponseEntity<UserDetailsCustomService> {
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDetailsCustomService)
    }


}