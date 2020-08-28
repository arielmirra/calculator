package com.calculator.controller

import com.calculator.model.*
import com.calculator.service.CalculableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/calculable")
class CalculableController(
        @Autowired private val calculableService: CalculableService
) {
    // TODO: add by id and use ID to handle saving and relationships
    @GetMapping("/{name}")
    fun getByName(@PathVariable name: String): Calculable? = calculableService.findByName(name)

    @PostMapping
    fun create(@RequestBody form: CalculableForm): Calculable? = calculableService.create(form)
}
