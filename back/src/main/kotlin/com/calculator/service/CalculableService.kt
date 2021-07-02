package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CalculableService(
    @Autowired private val calculableRepository: CalculableRepository,
    @Autowired private val formulaService: FormulaService
) {

    fun getAll(): List<Calculable> = calculableRepository.findAll()
    fun findById(id: Long): Calculable? = calculableRepository.findByIdOrNull(id)
    fun save(calculable: Calculable) = calculableRepository.save(calculable)
    fun deleteById(id: Long) = calculableRepository.deleteById(id)

    fun create(form: CalculableForm): Calculable {
        val calc: Calculable
        println(form)
        if (hasOperator(form)) {
            calc = if (hasTerms(form)) {
                val left = findById(form.left!!)
                val right = findById(form.right!!)
                Calculable(
                    name = form.name,
                    calculableType = CalculableType.METRIC,
                    left = left,
                    right = right,
                    operator = Operator.valueOf(form.operator!!)
                )
            } else {
                Calculable(
                    name = form.name,
                    calculableType = CalculableType.METRIC,
                    operator = Operator.valueOf(form.operator!!)
                )
            }
        } else {
            calc = Calculable(
                name = form.name,
                calculableType = CalculableType.VARIABLE,
                value = form.value
            )
        }
        calc.description = form.description
        return save(calc)
    }

    fun createFormula(id: Long): Formula? =
        findById(id)?.let{
            formulaService.createFromCalculable(it)
        }

    fun update(id: Long, form: CalculableForm): Boolean =
        findById(id)?.let {
            var changed = false
            if (hasOperator(form)) {
                val left = findById(form.left!!)
                val right = findById(form.right!!)
                it.left = left
                it.right = right
                it.description = form.description
                it.operator = Operator.valueOf(form.operator!!)
                save(it)
                changed = true
            } else if (form.value != null) {
                it.value = form.value
                save(it)
                changed = true
            }
            if (form.name != it.name) {
                it.name = form.name
                save(it)
                changed = true
            }
            return changed
        } ?: false

    fun delete(id: Long): Boolean =
        findById(id)?.let {
            deleteById(id)
            true
        } ?: false

    private fun hasOperator(form: CalculableForm) = form.operator != null
    private fun hasTerms(form: CalculableForm) = form.left != null && form.right != null
}
