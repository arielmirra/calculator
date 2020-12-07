package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MetricService(
    @Autowired private val metricRepository: MetricRepository
) {

    fun findByName(name: String): Metric? = metricRepository.findByName(name)
    fun findById(id: Long): Optional<Metric> = metricRepository.findById(id)
    fun save(metric: Metric) = metricRepository.save(metric)
    fun create(metric: Metric): Metric? {
        TODO("Not yet implemented")
    }

    fun getAll(): List<Any> = metricRepository.fetchAll()

}
