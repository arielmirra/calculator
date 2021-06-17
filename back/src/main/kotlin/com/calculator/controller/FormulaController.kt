package com.calculator.controller

import com.calculator.model.Formula
import com.calculator.model.Measurement
import com.calculator.service.FormulaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@CrossOrigin
@RequestMapping("/formula")
class FormulaController(
    @Autowired private val formulaService: FormulaService
) : WebApi<Formula> {
    @GetMapping()
    fun getAll(): List<Formula> = formulaService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Formula> =
        formulaService.findById(id)?.let { ok(it) } ?: notFound()

    @GetMapping("/from/{calcId}")
    fun getByCalcId(@PathVariable calcId: Long): List<Formula> = formulaService.findByCalcId(calcId)

    @PutMapping("/{id}")
    fun measure(
        @PathVariable id: Long,
        @RequestBody calc: Formula,
        b: UriComponentsBuilder
    ): ResponseEntity<Measurement> =
        formulaService.calculate(calc).let { ResponseEntity.ok(it) }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Boolean> =
        if (formulaService.delete(id)) ResponseEntity.ok(true) else notFoundBool()
}
