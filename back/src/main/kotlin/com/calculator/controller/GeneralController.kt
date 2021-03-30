package com.calculator.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping
// http://localhost:8080/swagger-ui.html
class GeneralController {

    @GetMapping("/")
    fun greet(): String = "Hello There!"
}
