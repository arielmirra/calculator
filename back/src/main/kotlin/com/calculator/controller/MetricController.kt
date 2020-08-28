package com.calculator.controller

import com.calculator.model.*
import com.calculator.service.MetricService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/metric")
class MetricController(
        @Autowired private val metricService: MetricService
) {

    @GetMapping("/{name}")
    fun getByName(@PathVariable name: String): Metric? = metricService.findByName(name)
//
//    @PostMapping
//    fun create(@RequestBody metric: Metric): Metric? = metricService.create(metric)
}
