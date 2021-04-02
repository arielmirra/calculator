package com.calculator.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
interface WebApi<T> {
    fun notFound() = ResponseEntity.notFound().build<T>()

    fun notFoundBool() = ResponseEntity.notFound().build<Boolean>()

    fun badRequest() = ResponseEntity.badRequest().build<T>()

    fun ok(body: T) = ResponseEntity.ok(body)
}
