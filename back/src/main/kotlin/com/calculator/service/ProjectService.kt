package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProjectService(
    @Autowired private val projectRepository: ProjectRepository,
    @Autowired private val calculableRepository: CalculableRepository
) {

    fun getAll(): List<Project> = projectRepository.findAll()
    fun findByName(name: String): Project? = projectRepository.findByName(name)
    fun findById(id: Long): Project? = projectRepository.findByIdOrNull(id)
    fun save(project: Project) = projectRepository.save(project)
    fun deleteById(id: Long) = projectRepository.deleteById(id)

    fun create(form: ProjectForm): Project? {
        val calculables = parseCalculables(form)

        val project = Project(
            name = form.name,
            description = form.description,
            calculables = calculables
        )

        return save(project)
    }

    fun update(id: Long, form: ProjectForm): Boolean = findById(id)?.let {
        it.name = form.name
        it.description = form.description
        it.calculables = parseCalculables(form)

        save(it)
        true
    } ?: false

    fun delete(id: Long): Boolean = findById(id)?.let {
        deleteById(id)
        true
    } ?: false

    private fun parseCalculables(form: ProjectForm): MutableSet<Calculable> {
        return form.calculables
            .map { calculableRepository.findById(it) }
            .filter { it.isPresent }
            .map { it.get() }
            .toMutableSet()
    }
}
