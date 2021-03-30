package com.calculator.service

import com.calculator.model.Calculable
import com.calculator.model.Metric
import com.calculator.model.MetricForm
import com.calculator.model.MetricRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MetricService(
    @Autowired private val metricRepository: MetricRepository,
    @Autowired private val calculableService: CalculableService
) {
    fun getAll(): List<Any> = metricRepository.fetchAllComplete()
    fun findByName(name: String): Metric? = metricRepository.findByName(name)
    fun findById(id: Long): Metric? = metricRepository.findBy_id(id)
    fun save(metric: Metric) = metricRepository.save(metric)
    fun deleteById(id: Long) = metricRepository.deleteById(id)

    fun create(form: MetricForm): Metric? {
        val p = parseArrays(form)

        val metric = Metric(
            name = form.name,
            description = form.description,
            metrics = p.first,
            calculates = p.second
        )

        return save(metric)
    }

    fun update(id: Long, form: MetricForm): Boolean = findById(id)?.let {
        val p = parseArrays(form)

        it.name = form.name
        it.description = form.description
        it.metrics = p.first
        it.calculates = p.second

        save(it)
        true
    } ?: false

    fun delete(id: Long): Boolean = findById(id)?.let {
        deleteById(id)
        true
    } ?: false

    private fun parseArrays(form: MetricForm): Pair<MutableSet<Metric>, MutableSet<Calculable>> {
        val metrics = form.metrics
            .map { findById(it) }
            .filterNotNull()
            .toMutableSet()

        val calculates = form.calculates
            .map { calculableService.findById(it) }
            .filterNotNull()
            .toMutableSet()

        return Pair(metrics, calculates)
    }
}
