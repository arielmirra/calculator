package com.calculator.controller

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping
class MeasurableController(
        @Autowired private val metricSetRepository: MetricSetRepository,
        @Autowired private val attributeRepository: AttributeRepository,
        @Autowired private val metricRepository: MetricRepository,
        @Autowired private val calculusRepository: CalculusRepository,
        @Autowired private val valueRepository: ValueRepository,
        @Autowired private val measurementRepository: MeasurementRepository
) {
    @GetMapping("/")
    fun greet(): String {
        return "Hello There!"
    }

    @GetMapping("/metrics")
    fun getAllMetricSets(): List<MetricSet> = metricSetRepository.findAll().toList()

    @GetMapping("/metrics/custom")
    fun getAllMetricSetsByQuery(): List<MetricSet> =
            metricSetRepository.getFullMetrics().toList()

    @GetMapping("/metric/{name}")
    fun getAllMetricSets(@PathVariable name: String): MetricSet? = metricSetRepository.findByName(name)

    @GetMapping("/metric")
    fun getAllMetrics(): List<Metric> = metricRepository.findAll().toList()

    @GetMapping("attribute")
    fun getAllAttributes(): List<Attribute> = attributeRepository.findAll().toList()

    @GetMapping("/calculable")
    fun getAllCalculable(): List<Calculable> = calculusRepository.findAll().toList()

    @GetMapping("/value")
    fun getAllValues(): List<Value> = valueRepository.findAll().toList()
}
