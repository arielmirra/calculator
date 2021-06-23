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
        putValues(formula.calcTree, formula.variables)
        return measurementRepository.save(Measurement(
            value = formula.calcTree.calculate(),
            from = formula,
        ))
    }

    private fun getValues(calculable: Calculable, variables: MutableMap<String, Double>) {
        if (calculable.operator != null) {
            getValues(calculable.left!!, variables)
            getValues(calculable.right!!, variables)
        }
        else variables[calculable.name] = 0.0
    }

    private fun putValues(calculable: Calculable, variables: MutableMap<String, Double>) {
        if (variables.containsKey(calculable.name)) {
            calculable.value = variables[calculable.name]
        }
        else {
            putValues(calculable.left!!, variables)
            putValues(calculable.right!!, variables)
        }
    }
}
