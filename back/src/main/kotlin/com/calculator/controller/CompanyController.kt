package com.calculator.controller

import com.calculator.model.Company
import com.calculator.model.CompanyForm
import com.calculator.service.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@CrossOrigin
@RequestMapping("/company")
class CompanyController(
    @Autowired private val companyService: CompanyService
) {
    @GetMapping()
    fun getAll(): List<Company> = companyService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Company> {
        val optional = companyService.findById(id)
        return optional.map{ c -> ResponseEntity.ok(c)}.orElse(ResponseEntity.notFound().build())
    }


    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Company> {
        val optional = companyService.findByName(name)
        return if (optional != null) ResponseEntity.ok(optional)
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody form: CompanyForm, b: UriComponentsBuilder): ResponseEntity<Company> {
        val created = companyService.create(form)
        val components = b.path("/company/{id}").buildAndExpand(created)
        return if (created != null) ResponseEntity.created(components.toUri()).build()
        else ResponseEntity.badRequest().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody form: CompanyForm, b: UriComponentsBuilder): ResponseEntity<Boolean> {
        return if (companyService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> {
        return if (companyService.delete(id)) ResponseEntity.ok(true) else ResponseEntity.notFound().build()
    }
}
