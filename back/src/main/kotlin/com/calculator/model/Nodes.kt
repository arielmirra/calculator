package com.calculator.model

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.time.Instant
import java.util.*
import java.util.function.BinaryOperator
import java.util.function.DoubleBinaryOperator


interface Node {
    val _type: String
    val _id: Long

}


@NodeEntity
data class Metric(
    @Id @GeneratedValue override val _id: Long = -1,
    override val _type: String = "Metric",
    var name: String = "",
    var description: String = "",
    var qualityCharacteristic: QualityCharacteristic = QualityCharacteristic.NONE,
    var qualitySubCharacteristic: QualitySubCharacteristic = QualitySubCharacteristic.NONE,
    @Relationship(type = "MEASURES")
    var metrics: MutableSet<Metric> = mutableSetOf(),
    @Relationship(type = "CALCULATES")
    var calculates: MutableSet<Calculable> = mutableSetOf()
) : Node {
    fun measures(metric: Metric) = metrics.add(metric)

    fun measure(): Measurement {
        var result = if (metrics.isEmpty()) 0.0 else metrics.map { m -> m.measure().value }.sum()
        if (calculates.isNotEmpty()) result += calculate()
        return Measurement(
            name = "Resultado de la métrica $name",
            value = result
        )
    }

    fun calculates(calculable: Calculable) = calculates.add(calculable)

    private fun calculate(): Double {
        return if (calculates.isNotEmpty()) calculates.map { c -> c.calculate() }.sum()
        else 0.0
    }
}

enum class QualityCharacteristic {
    FUNCTIONAL_SUITABILITY,
    EFFECTIVENESS,
    EFFICIENCY,
    EVOLVABILITY,
    MAINTAINABILITY,
    RELIABILITY,
    SAFETY,
    SECURITY,
    TESTABILITY,
    USABILITY,
    NONE,
    PERFORMANCE_EFFICIENCY,
    COMPATIBILITY
}

enum class QualitySubCharacteristic {
    TIME_BEHAVIOR,
    RESOURCE_UTILIZATION,
    ACCURACY,
    CODE_COMPLEXITY_AND_REGRESSION_TESTING,
    USABILITY_TESTING,
    NONE,
    FUNCTIONAL_COMPLETENESS,
    FUNCTIONAL_CORRECTNESS,
    FUNCTIONAL_APPROPRIATENESS,
    CAPACITY,
    INTEROPERABILITY,
    COEXISTENCE,
    ACCESSIBILITY,
    USER_INTERFACE_AESTHETICS,
    USER_ERROR_PROTECTION,
    OPERABILITY,
    APPROPRIATENESS_RECOGNISABILITY,
    LEARNABILITY
}


val qualityCharacteristics = mutableSetOf(
    "effectiveness",
    "efficiency",
    "evolvability",
    "maintainability",
    "reliability",
    "safety",
    "security",
    "testability",
    "usability",
    "none")


@NodeEntity
data class Calculable(
    @Id @GeneratedValue override val _id: Long = -1,
    override val _type: String = "Calculable",
    var name: String = "",
    @Relationship(type = "LEFT")
    var left: Calculable? = null,
    @Relationship(type = "RIGHT")
    var right: Calculable? = null,
    var operator: Operator? = null,
    var value: Double? = null
) : Node {
    fun calculate(): Double {
        return if (value != null) value!!
        else operator!!.apply(left!!.calculate(), right!!.calculate())
    }
}

enum class Operator : BinaryOperator<Double>, DoubleBinaryOperator {
    PLUS {
        override fun apply(t: Double, u: Double): Double = t + u
    },
    MINUS {
        override fun apply(t: Double, u: Double): Double = t - u
    },
    TIMES {
        override fun apply(t: Double, u: Double): Double = t * u
    },
    DIVIDE {
        override fun apply(t: Double, u: Double): Double = t / u
    };

    override fun applyAsDouble(t: Double, u: Double) = apply(t, u)
}

@NodeEntity
data class Measurement(
    @Id @GeneratedValue val id: Long = -1,
    val name: String = "",
    val value: Double = 0.0,
    val date: Date = Date.from(Instant.now())!!
)
