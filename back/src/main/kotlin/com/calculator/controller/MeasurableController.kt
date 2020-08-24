package com.calculator.controller

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping
// http://localhost:8080/swagger-ui.html
class MeasurableController(
        @Autowired private val metricRepository: MetricRepository,
        @Autowired private val calculableRepository: CalculableRepository
) {
    @GetMapping("/")
    fun greet(): String = "Hello There!"

    @GetMapping("/metric")
    fun getAllMetrics(): List<Metric> = metricRepository.findAll().toList()

    @GetMapping("/calculable")
    fun getAllCalculable(): List<Calculable> = calculableRepository.findAll().toList()
}
