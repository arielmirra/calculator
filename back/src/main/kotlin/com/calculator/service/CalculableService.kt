package com.calculator.service

import com.calculator.model.Calculable
import com.calculator.model.CalculableForm
import com.calculator.model.CalculableRepository
import com.calculator.model.Operator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CalculableService(
    @Autowired private val calculableRepository: CalculableRepository
) {

    fun getAll(): List<Calculable> = calculableRepository.findAll()
    fun findByName(name: String): Calculable? = calculableRepository.findByName(name)
    fun findById(id: Long): Calculable? = calculableRepository.findBy_id(id)
    fun save(calculable: Calculable) = calculableRepository.save(calculable)
    fun deleteById(id: Long) = calculableRepository.deleteById(id)

    fun create(form: CalculableForm): Calculable {
        val calc: Calculable
        if (hasOperator(form)) {
            if (hasTerms(form)) {
                val left = findById(form.left!!)
                val right = findById(form.right!!)
                calc = Calculable(
                    name = form.name,
                    left = left,
                    right = right,
                    operator = Operator.valueOf(form.operator!!)
                )
            } else {
                calc = Calculable(
                    name = form.name,
                    operator = Operator.valueOf(form.operator!!)
                )
            }
        } else {
            calc = Calculable(
                name = form.name,
                value = form.value
            )
        }
        return save(calc)
    }

    fun calculate(id: Long): Double? = findById(id)?.calculate()

    fun update(id: Long, form: CalculableForm): Boolean =
        findById(id)?.let {
            var changed = false
            if (hasOperator(form)) {
                val left = findById(form.left!!)
                val right = findById(form.right!!)
                it.left = left
                it.right = right
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
            if (calculableRepository.calcUsage(id).isEmpty()) {
                deleteById(id)
                true
            } else false
        } ?: false

    private fun hasOperator(form: CalculableForm) = form.operator != null
    private fun hasTerms(form: CalculableForm) = form.left != null && form.right != null
}
