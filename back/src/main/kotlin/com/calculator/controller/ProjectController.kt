package com.calculator.controller

import com.calculator.model.Project
import com.calculator.model.ProjectForm
import com.calculator.service.ProjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@CrossOrigin
@RequestMapping("/project")
class ProjectController(
    @Autowired private val projectService: ProjectService
): WebApi<Project> {
    @GetMapping()
    fun getAll(): List<Any> = projectService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Project> =
        projectService.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Project> =
        projectService.findByName(name)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody form: ProjectForm, b: UriComponentsBuilder): ResponseEntity<Project> =
        try {
            val created = projectService.create(form)
            val components = b.path("/calculable/{id}").buildAndExpand(created)
            ResponseEntity.created(components.toUri()).build()
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody form: ProjectForm,
        b: UriComponentsBuilder
    ): ResponseEntity<Boolean> =
        if (projectService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> =
        if (projectService.delete(id)) ResponseEntity.ok(true) else ResponseEntity.notFound().build()
}
