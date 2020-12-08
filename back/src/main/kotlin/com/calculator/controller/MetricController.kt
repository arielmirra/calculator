package com.calculator.controller

import com.calculator.model.*
import com.calculator.service.MetricService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@CrossOrigin
@RequestMapping("/metric")
class MetricController(
        @Autowired private val metricService: MetricService
) {
    @GetMapping()
    fun getAll(): List<Any> = metricService.getAll()
    
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Metric> {
        val optional = metricService.findById(id)
        return optional.map{ c -> ResponseEntity.ok(c)}.orElse(ResponseEntity.notFound().build())
    }


    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Metric> {
        val optional = metricService.findByName(name)
        return if (optional != null) ResponseEntity.ok(optional)
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/measure/{id}")
    fun measure(@PathVariable id: Long): ResponseEntity<Measurement> {
        val optional = metricService.findById(id)
        return optional.map{ m -> ResponseEntity.ok(m.measure())}.orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/measure/name/{name}")
    fun measure(@PathVariable name: String): ResponseEntity<Measurement> {
        val optional = metricService.findByName(name) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(optional.measure())
    }

    @PostMapping
    fun create(@RequestBody form: MetricForm, b: UriComponentsBuilder): ResponseEntity<Metric> {
        val created = metricService.create(form)
        val components = b.path("/calculable/{id}").buildAndExpand(created)
        return if (created != null) ResponseEntity.created(components.toUri()).build()
        else ResponseEntity.badRequest().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody form: MetricForm, b: UriComponentsBuilder): ResponseEntity<Boolean> {
        return if (metricService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> {
        return if (metricService.delete(id)) ResponseEntity.ok(true) else ResponseEntity.notFound().build()
    }
}
