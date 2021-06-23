package com.calculator.controller

import com.calculator.model.Measurement
import com.calculator.service.MeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/measurement")
class MeasurementController(
    @Autowired private val measurementService: MeasurementService
) : WebApi<Measurement> {

    @GetMapping()
    fun getAll(): List<Measurement> = measurementService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Measurement> =
        measurementService.findById(id)?.let { ok(it) } ?: notFound()

    @GetMapping("/from/{id}")
    fun getAllFromMetric(@PathVariable id: Long): List<Measurement> =
        measurementService.findAllFromMetricId(id)
}
