package com.calculator.controller

import com.calculator.model.Calculus
import com.calculator.model.Measurement
import com.calculator.service.CalculusService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@CrossOrigin
@RequestMapping("/calculus")
class CalculusController(
    @Autowired private val calculusService: CalculusService
) : WebApi<Calculus> {
    @GetMapping()
    fun getAll(): List<Calculus> = calculusService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Calculus> =
        calculusService.findById(id)?.let { ok(it) } ?: notFound()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody calc: Calculus,
        b: UriComponentsBuilder
    ): ResponseEntity<Measurement> =
        calculusService.calculate(calc).let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> =
        if (calculusService.delete(id)) ResponseEntity.ok(true) else notFoundBool()
}
