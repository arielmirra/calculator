package com.calculator.controller

import com.calculator.model.Calculable
import com.calculator.model.CalculableForm
import com.calculator.service.CalculableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@CrossOrigin
@RequestMapping("/calculable")
class CalculableController(
    @Autowired private val calculableService: CalculableService
) : WebApi<Calculable> {
    @GetMapping()
    fun getAll(): List<Calculable> = calculableService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Calculable> =
        calculableService.findById(id)?.let { ok(it) } ?: notFound()


    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Calculable> =
        calculableService.findByName(name)?.let { ok(it) } ?: notFound()

    @GetMapping("/calculate/{id}")
    fun calculate(@PathVariable id: Long): ResponseEntity<Double> =
        calculableService.calculate(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody form: CalculableForm, b: UriComponentsBuilder): ResponseEntity<Calculable> =
        try {
            val created = calculableService.create(form)
            val components = b.path("/calculable/{id}").buildAndExpand(created)
            ResponseEntity.created(components.toUri()).build()
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody form: CalculableForm,
        b: UriComponentsBuilder
    ): ResponseEntity<Boolean> {
        return if (calculableService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> {
        return if (calculableService.delete(id)) ResponseEntity.ok(true) else notFoundBool()
    }
}
