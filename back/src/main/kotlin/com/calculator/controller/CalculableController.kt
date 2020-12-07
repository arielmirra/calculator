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
) {
    @GetMapping()
    fun getAll(): List<Calculable> = calculableService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Calculable> {
        val optional = calculableService.findById(id)
        return optional.map{ c -> ResponseEntity.ok(c)}.orElse(ResponseEntity.notFound().build())
    }


    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Calculable> {
        val optional = calculableService.findByName(name)
        return if (optional != null) ResponseEntity.ok(optional)
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/calculate/{id}")
    fun calculate(@PathVariable id: Long): ResponseEntity<Double> {
        val optional = calculableService.findById(id)
        return optional.map{ c -> ResponseEntity.ok(c.calculate())}.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun create(@RequestBody form: CalculableForm, b: UriComponentsBuilder): ResponseEntity<Calculable> {
        val created = calculableService.create(form)
        val components = b.path("/calculable/{id}").buildAndExpand(created)
        return if (created != null) ResponseEntity.created(components.toUri()).build()
        else ResponseEntity.badRequest().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody form: CalculableForm, b: UriComponentsBuilder): ResponseEntity<Boolean> {
        return if (calculableService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> {
        return if (calculableService.delete(id)) ResponseEntity.ok(true) else ResponseEntity.notFound().build()
    }
}
