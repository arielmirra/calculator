package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyService(
    @Autowired private val companyRepository: CompanyRepository
) {

    fun getAll(): List<Company> = companyRepository.findAll().toList()
    fun findByName(name: String): Company? = companyRepository.findByName(name)
    fun findById(id: Long): Optional<Company> = companyRepository.findBy_id(id)
    fun save(company: Company) = companyRepository.save(company)
    fun deleteById(id: Long) = companyRepository.deleteById(id)

    fun create(form: CompanyForm): Company? {
        return null
    }

    fun update(id: Long, form: CompanyForm): Boolean {
        val company = findById(id)
        if(!company.isPresent) return false
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
}
