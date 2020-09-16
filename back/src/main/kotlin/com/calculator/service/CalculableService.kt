package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CalculableService(
        @Autowired private val calculableRepository: CalculableRepository
) {

    fun findByName(name: String): Calculable? = calculableRepository.findByName(name)
    fun findById(id: Long): Optional<Calculable> = calculableRepository.findById(id)
    fun save(calculable: Calculable) = calculableRepository.save(calculable)

    fun create(form: CalculableForm): Calculable? {
        println(form)
        val isComposite = form.left != null && form.right != null && form.operator != null
        if (isComposite) {
            val left = calculableRepository.findById(form.left!!)
            val right = calculableRepository.findById(form.right!!)

            if (left.isPresent && right.isPresent) {
                println(left)
                println(right)
                val calc = Calculable(
                        name = form.name,
                        left = left.get(),
                        right = right.get(),
                        operator = Operator.valueOf(form.operator!!),
                        value = form.value
                )
                val result = calculableRepository.save(calc)
                println(result)
                return result
            } else if (form.value != null) {
                val calc = Calculable(
                        name = form.name,
                        value = form.value
                )
                val result = calculableRepository.save(calc)
                println(result)
                return result
            }
        }
        return null
    }

    fun delete(id: Long): Boolean {
        return if (findById(id).isPresent) {
            calculableRepository.deleteById(id)
            true
        } else false
    }

}
