package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectService(
    @Autowired private val projectRepository: ProjectRepository,
    @Autowired private val metricRepository: MetricRepository
) {

    fun getAll(): List<Any> = projectRepository.fetchAll()
    fun findByName(name: String): Project? = projectRepository.findByName(name)
    fun findById(id: Long): Project? = projectRepository.findBy_id(id)
    fun save(project: Project) = projectRepository.save(project)
    fun deleteById(id: Long) = projectRepository.deleteById(id)

    fun create(form: ProjectForm): Project? {
        val measurements = parseMetrics(form)

        val project = Project(
            name = form.name,
            description = form.description,
            metrics = measurements
        )

        return save(project)
    }

    fun update(id: Long, form: ProjectForm): Boolean = findById(id)?.let {
        it.name = form.name
        it.description = form.description
        it.metrics = parseMetrics(form)

        save(it)
        true
    } ?: false

    fun delete(id: Long): Boolean = findById(id)?.let {
        deleteById(id)
        true
    } ?: false

    private fun parseMetrics(form: ProjectForm): MutableSet<Metric> {
        return form.measurements
            .map { metricRepository.findById(it) }
            .filter { it.isPresent }
            .map { it.get() }
            .toMutableSet()
    }
}
