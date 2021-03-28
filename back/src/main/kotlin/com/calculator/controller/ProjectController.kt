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
) {
    @GetMapping()
    fun getAll(): List<Any> = projectService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Project> {
        val optional = projectService.findById(id)
        return optional.map{ c -> ResponseEntity.ok(c)}.orElse(ResponseEntity.notFound().build())
    }


    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name: String): ResponseEntity<Project> {
        val optional = projectService.findByName(name)
        return if (optional != null) ResponseEntity.ok(optional)
        else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@RequestBody form: ProjectForm, b: UriComponentsBuilder): ResponseEntity<Project> {
        val created = projectService.create(form)
        val components = b.path("/project/{id}").buildAndExpand(created)
        return if (created != null) ResponseEntity.created(components.toUri()).build()
        else ResponseEntity.badRequest().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody form: ProjectForm, b: UriComponentsBuilder): ResponseEntity<Boolean> {
        return if (projectService.update(id, form)) ResponseEntity.ok(true) else ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> {
        return if (projectService.delete(id)) ResponseEntity.ok(true) else ResponseEntity.notFound().build()
    }
}
