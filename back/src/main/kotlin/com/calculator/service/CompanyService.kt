package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyService(
        @Autowired private val companyRepository: CompanyRepository,
        @Autowired private val projectRepository: ProjectRepository
) {

    fun getAll(): List<Any> = companyRepository.fetchAll()
    fun findByName(name: String): Company? = companyRepository.findByName(name)
    fun findById(id: Long): Optional<Company> = companyRepository.findBy_id(id)
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

    fun update(id: Long, form: CompanyForm): Boolean {
        //todo: update company
        val company = findById(id)
        if (!company.isPresent) return false
        val p = company.get()

        var changed = false
        return changed
    }

    fun delete(id: Long): Boolean {
        return if (findById(id).isPresent) {
            deleteById(id)
            true
        } else false
    }

    private fun parseProjects(form: CompanyForm): MutableSet<Project> {
        return form.projects
                .map { projectRepository.findById(it) }
                .filter { it.isPresent }
                .map { it.get() }
                .toMutableSet()
    }
}
