package com.calculator.controller

import com.calculator.model.Measurement
import com.calculator.model.Metric
import com.calculator.model.MetricForm
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
): WebApi<Metric> {
    @GetMapping()
    fun getAll(): List<Any> = metricService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Metric> =
        metricService.findById(id)?.let { ok(it) } ?: notFound()


    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Metric> =
        metricService.findByName(name)?.let { ok(it) } ?: notFound()

    @GetMapping("/measure/{id}")
    fun measure(@PathVariable id: Long): ResponseEntity<Measurement> =
        metricService.findById(id)?.let { ResponseEntity.ok(it.measure()) } ?: ResponseEntity.notFound().build()

    @GetMapping("/measure/name/{name}")
    fun measure(@PathVariable name: String): ResponseEntity<Measurement> =
        metricService.findByName(name)?.let { ResponseEntity.ok(it.measure()) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody form: MetricForm, b: UriComponentsBuilder): ResponseEntity<Metric> =
        try {
            val created = metricService.create(form)
            val components = b.path("/calculable/{id}").buildAndExpand(created)
            ResponseEntity.created(components.toUri()).build()
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody form: MetricForm,
        b: UriComponentsBuilder
    ): ResponseEntity<Boolean> =
        if (metricService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> =
        if (metricService.delete(id)) ResponseEntity.ok(true) else notFoundBool()
}
