package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CalculusService(
    @Autowired private val calculusRepository: CalculusRepository,
    @Autowired private val calculableRepository: CalculableRepository,
    @Autowired private val measurementRepository: MeasurementRepository,
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
        getValues(calculus.calcTree, calculus.values)
        return calculusRepository.save(calculus)
    }


    private fun getValues(calculable: Calculable, values: MutableSet<Calculable>) {
        if (calculable.operator != null) {
            getValues(calculable.left!!, values)
            getValues(calculable.right!!, values)
        }
        else {
            println("a leaf")
            println(calculable)
            values.add(calculable)
        }
    }

    fun calculate(calc: Calculus): Measurement {
        // complete calcTree with provided values
        return Measurement(
            name = "Measurement",
//            value = calc.calcTree.calculate(),
            value = 1.0,
            from = Metric(),
            metricId = calc.calcTree.id
        )
    }
}
