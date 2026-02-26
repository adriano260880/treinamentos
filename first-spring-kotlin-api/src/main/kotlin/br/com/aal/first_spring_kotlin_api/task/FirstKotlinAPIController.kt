package br.com.aal.first_spring_kotlin_api.task

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class FirstKotlinAPIController {

    @GetMapping
    fun getName(): String = "Hello Kotlin!!"
}