package me.dio.credit.application.system.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/admin")
class AdminResource() {

    @GetMapping("/report")
    fun report(): String {
        return "This is a Report. Only Admin can see it!"
    }

}