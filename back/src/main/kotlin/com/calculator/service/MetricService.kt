package com.calculator.service

import com.calculator.model.Calculable
import com.calculator.model.Metric
import com.calculator.model.MetricForm
import com.calculator.model.MetricRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MetricService(
    @Autowired private val metricRepository: MetricRepository,
    @Autowired private val calculableService: CalculableService
) {
    fun getAll(): List<Any> = metricRepository.fetchAllComplete()
    fun findByName(name: String): Metric? = metricRepository.findByName(name)
    fun findById(id: Long): Optional<Metric> = metricRepository.findBy_id(id)
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

    fun update(id: Long, form: MetricForm): Boolean {
        val metric = findById(id)
        if (!metric.isPresent) return false
        val m = metric.get()
        val p = parseArrays(form)

        m.name = form.name
        m.description = form.description
        m.metrics = p.first
        m.calculates = p.second

        save(m)
        return true
    }

    fun delete(id: Long): Boolean {
        return if (findById(id).isPresent) {
            deleteById(id)
            true
        } else false
    }

    private fun parseArrays(form: MetricForm): Pair<MutableSet<Metric>, MutableSet<Calculable>> {
        val metrics = form.metrics
            .map { findById(it) }
            .filter { it.isPresent }
            .map { it.get() }
            .toMutableSet()

        val calculates = form.calculates
            .map { calculableService.findById(it) }
            .filter { it.isPresent }
            .map { it.get() }
            .toMutableSet()

        return Pair(metrics, calculates)
    }
}
