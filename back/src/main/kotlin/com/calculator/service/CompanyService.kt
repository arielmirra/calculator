package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyService(
    @Autowired private val companyRepository: CompanyRepository,
    @Autowired private val projectRepository: ProjectRepository
) {

    fun getAll(): List<Company> = companyRepository.findAll()
    fun findByName(name: String): Company? = companyRepository.findByName(name)
    fun findById(id: Long): Company? = companyRepository.findBy_id(id)
    fun save(company: Company) = companyRepository.save(company)
    fun deleteById(id: Long) = companyRepository.deleteById(id)

    fun create(form: CompanyForm): Company? {
        val projects = parseProjects(form)

        val project = Company(
            name = form.name,
            description = form.description,
            projects = projects
        )

        return save(project)
    }

    fun update(id: Long, form: CompanyForm): Boolean = findById(id)?.let {
        it.name = form.name
        it.description = form.description
        it.projects = parseProjects(form)

        save(it)
        true
    } ?: false

    fun delete(id: Long): Boolean = findById(id)?.let {
        deleteById(id)
        true
    } ?: false

    private fun parseProjects(form: CompanyForm): MutableSet<Project> {
        return form.projects
            .map { projectRepository.findById(it) }
            .filter { it.isPresent }
            .map { it.get() }
            .toMutableSet()
    }
}
