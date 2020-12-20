package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CalculableService(
        @Autowired private val calculableRepository: CalculableRepository
) {

    fun getAll(): List<Calculable> = calculableRepository.findAll().toList()
    fun findByName(name: String): Calculable? = calculableRepository.findByName(name)
    fun findById(id: Long): Optional<Calculable> = calculableRepository.findBy_id(id)
    fun save(calculable: Calculable) = calculableRepository.save(calculable)
    fun deleteById(id: Long) = calculableRepository.deleteById(id)

    fun create(form: CalculableForm): Calculable? {
        if (isComposite(form)) {
            val left = findById(form.left!!)
            val right = findById(form.right!!)
            if (left.isPresent && right.isPresent) {
                val calc = Calculable(
                        name = form.name,
                        left = left.get(),
                        right = right.get(),
                        operator = Operator.valueOf(form.operator!!),
                        value = form.value
                )
                return save(calc)
            }
        } else if (form.value != null) {
            val calc = Calculable(
                    name = form.name,
                    value = form.value
            )
            return save(calc)
        }
        return null
    }

    fun update(id: Long, form: CalculableForm): Boolean {
        val calc = findById(id)
        if(calc.isEmpty) return false
        val calculus = calc.get()
        var changed = false
        if (isComposite(form)) {
            val left = findById(form.left!!)
            val right = findById(form.right!!)
            if (left.isPresent && right.isPresent) {
                calculus.left = left.get()
                calculus.right = right.get()
                calculus.operator = Operator.valueOf(form.operator!!)
                save(calculus)
                changed = true
            }
        } else if (form.value != null) {
            calculus.value = form.value
            save(calculus)
            changed = true
        }
        if (form.name != calculus.name) {
            calculus.name = form.name
            save(calculus)
            changed = true
        }
        return changed
    }

    fun delete(id: Long): Boolean {
        return if (findById(id).isPresent && calculableRepository.calcUsage(id).isEmpty()) {
            deleteById(id)
            true
        } else false
    }

    private fun isComposite(form: CalculableForm) = form.left != null && form.right != null && form.operator != null
}
