package com.calculator.controller

import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping
// http://localhost:8080/swagger-ui.html
class GeneralController {

    @GetMapping("/")
    fun greet(): String = "Hello There!"
}
