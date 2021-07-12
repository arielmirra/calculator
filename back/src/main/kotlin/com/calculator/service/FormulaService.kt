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
    fun deleteById(id: Long) = formulaRepository.deleteById(id)

    fun delete(id: Long): Boolean =
        findById(id)?.let {
            deleteById(it.id)
            val connectedNodes = mutableSetOf<Calculable>()
            treeToList(it.calcTree, connectedNodes)
            calculableRepository.deleteAll(connectedNodes)
            true
        } ?: false

    fun createFromCalculable(calculable: Calculable): Formula {
        val formula = Formula(
            calcTree = calculable.deepCopy(),
            fromId = calculable.id
        )
        buildVariablesMap(formula.calcTree, formula.variables)
        val result = formulaRepository.save(formula)
        return formulaRepository.findByIdOrNull(result.id)!!
    }

    fun calculate(formula: Formula): Measurement {
        println("calculating formula from: ${formula.calcTree.name}")
        assignVariablesValues(formula.calcTree, formula.variables)
        val result = measurementRepository.save(Measurement(
            value = formula.calcTree.calculate(),
            from = formula,
        ))
        measureInternalMetrics(formula.calcTree.left!!, formula.variables)
        measureInternalMetrics(formula.calcTree.right!!, formula.variables)
        return result
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

    private fun buildVariablesMap(calculable: Calculable, variables: MutableMap<String, Double>) {
        when (calculable.operator) {
            Operator.SUMMATION -> calculable.children.forEach { buildVariablesMap(it, variables) }
            Operator.AVERAGE -> calculable.children.forEach { buildVariablesMap(it, variables) }
            else -> {
                if (isSimpleMetric(calculable)) {
                    buildVariablesMap(calculable.left!!, variables)
                    buildVariablesMap(calculable.right!!, variables)
                } else variables[calculable.name] = getPreviousValue(calculable)
            }
        }
    }

    private fun isSimpleMetric(calculable: Calculable): Boolean =
        calculable.operator != null  && calculable.operator != Operator.AVERAGE && calculable.operator != Operator.SUMMATION

    private fun getPreviousValue(calculable: Calculable): Double {
        val previousValues = calculableRepository
            .findAllByNameOrderByLastMeasuredDesc(calculable.name)
            .filter { it.calculableType == CalculableType.COPY && it.value != null }
        return if (previousValues.isEmpty()) 0.0 else previousValues[0].value!!
    }

    private fun assignVariablesValues(calculable: Calculable, variables: MutableMap<String, Double>) {
        if (variables.containsKey(calculable.name)) {
            calculable.value = variables[calculable.name]
        } else {
            when (calculable.operator) {
                Operator.SUMMATION -> calculable.children.forEach { assignVariablesValues(it, variables) }
                Operator.AVERAGE -> calculable.children.forEach { assignVariablesValues(it, variables) }
                else -> {
                    assignVariablesValues(calculable.left!!, variables)
                    assignVariablesValues(calculable.right!!, variables)
                }
            }
        }
    }

    private fun treeToList(calculable: Calculable, nodes: MutableSet<Calculable>) {
        nodes.add(calculable)
        if (calculable.children.isNotEmpty()) calculable.children.forEach { treeToList(it, nodes) }
        calculable.left?.let { treeToList(it, nodes) }
        calculable.right?.let { treeToList(it, nodes) }
    }
}
