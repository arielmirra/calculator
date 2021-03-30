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
    fun getAll(): List<Any> = companyService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Company> =
        companyService.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()


    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Company> =
        companyService.findByName(name)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody form: CompanyForm, b: UriComponentsBuilder): ResponseEntity<Company> =
        try {
            val created = companyService.create(form)
            val components = b.path("/calculable/{id}").buildAndExpand(created)
            ResponseEntity.created(components.toUri()).build()
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody form: CompanyForm,
        b: UriComponentsBuilder
    ): ResponseEntity<Boolean> =
        if (companyService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> =
        if (companyService.delete(id)) ResponseEntity.ok(true) else ResponseEntity.notFound().build()
}
