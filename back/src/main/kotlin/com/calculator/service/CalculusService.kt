package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CalculusService(
    @Autowired private val calculusRepository: CalculusRepository,
    @Autowired private val calculableRepository: CalculableRepository
) {

    fun getAll(): List<Calculus> = calculusRepository.findAll()
    fun findById(id: Long): Calculus? = calculusRepository.findByIdOrNull(id)
    fun save(Calculus: Calculus) = calculusRepository.save(Calculus)
    fun deleteById(id: Long) = calculusRepository.deleteById(id)

    fun delete(id: Long): Boolean =
        findById(id)?.let {
            deleteById(it.id)
            true
        } ?: false

    fun createFromCalculable(calculable: Calculable): Calculus {
        val copy = calculable.deepCopy()
        val savedCopy = calculableRepository.save(copy)
        val savedCopyFromRepository = calculableRepository.findByIdOrNull(savedCopy.id)
        val calculus = Calculus(calcTree = savedCopyFromRepository!!)
        getValuesSet(calculus.calcTree, calculus.values)
        calculusRepository.save(calculus)
        return calculusRepository.save(calculus)
    }


    private fun getValuesSet(calculable: Calculable, values: MutableSet<Long>) {
        if (calculable.operator != null) {
            getValuesSet(calculable.left!!, values)
            getValuesSet(calculable.right!!, values)
        }
        else {
            println("a leaf")
            println(calculable)
            values.add(calculable.id)
        }
    }


}
