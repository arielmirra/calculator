package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FormulaService(
    @Autowired private val formulaRepository: FormulaRepository,
    @Autowired private val calculableRepository: CalculableRepository,
    @Autowired private val measurementRepository: MeasurementRepository,
) {

    fun getAll(): List<Formula> = formulaRepository.findAll()
    fun findById(id: Long): Formula? = formulaRepository.findByIdOrNull(id)
    fun findByCalcId(id: Long): List<Formula> = formulaRepository.findAllByCalcTreeId(id)
    fun save(Formula: Formula) = formulaRepository.save(Formula)
    fun deleteById(id: Long) = formulaRepository.deleteById(id)

    fun delete(id: Long): Boolean =
        findById(id)?.let {
            deleteById(it.id)
            true
        } ?: false

    fun createFromCalculable(calculable: Calculable): Formula {
        val formula = Formula(
            calcTree = calculable.deepCopy(),
            fromId = calculable.id
        )
        getValues(formula.calcTree, formula.variables)
        val result = formulaRepository.save(formula)
        return formulaRepository.findByIdOrNull(result.id)!!
    }

    fun calculate(formula: Formula): Measurement {
        println("calculating formula from: ${formula.calcTree.name}")
        putValues(formula.calcTree, formula.variables)
        measureInternalMetrics(formula.calcTree.left!!, formula.variables)
        measureInternalMetrics(formula.calcTree.right!!, formula.variables)
        formula.calcTree.left
        formula.calcTree.right
        return measurementRepository.save(Measurement(
            value = formula.calcTree.calculate(),
            from = formula,
        ))
    }

    private fun measureInternalMetrics(calculable: Calculable, variables: MutableMap<String, Double>): Unit {
        if (calculable.description != "") {
            println("measuring internal metric -> ${calculable.name}")
            val original = calculableRepository.findByNameAndCalculableType(calculable.name, CalculableType.METRIC)[0]
            val formula = createFromCalculable(original)
            formula.variables = variables
            calculate(formula)
            calculable.left?.let { measureInternalMetrics(it, variables) }
            calculable.right?.let { measureInternalMetrics(it, variables) }
        }
    }

    private fun getValues(calculable: Calculable, variables: MutableMap<String, Double>) {
        if (calculable.operator != null) {
            getValues(calculable.left!!, variables)
            getValues(calculable.right!!, variables)
        } else variables[calculable.name] = getPreviousValue(calculable)
    }

    private fun getPreviousValue(calculable: Calculable): Double {
        val previousValues = calculableRepository
            .findAllByNameOrderByLastMeasuredDesc(calculable.name)
            .filter { it.calculableType == CalculableType.COPY }
        return if (previousValues.isEmpty()) 0.0 else previousValues[0].value!!
    }

    private fun putValues(calculable: Calculable, variables: MutableMap<String, Double>) {
        if (variables.containsKey(calculable.name)) {
            calculable.value = variables[calculable.name]
        } else {
            putValues(calculable.left!!, variables)
            putValues(calculable.right!!, variables)
        }
    }
}
