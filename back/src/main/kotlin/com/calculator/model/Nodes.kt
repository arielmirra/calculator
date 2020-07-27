package com.calculator.model

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.function.BinaryOperator
import java.util.function.DoubleBinaryOperator

@NodeEntity
data class Attribute(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = ""
)

@NodeEntity
data class MetricSet(
        @Id @GeneratedValue
        val id: Long = -1,
        val name: String = "",
        val description: String = "",
        @Relationship(type = "HAS_ATTRIBUTE", direction = Relationship.OUTGOING)
        val attributes: MutableSet<Attribute> = mutableSetOf(),
        @Relationship(type = "MEASURES", direction = Relationship.OUTGOING)
        val measures: MutableSet<Measurable> = mutableSetOf()

) : Measurable {
    fun hasAttribute(attribute: Attribute) = attributes.add(attribute)
    fun measures(measurable: Measurable) = measures.add(measurable)

    override fun measure() {
        // TODO should save the measurement
        measures.map { m -> m.measure() }
    }
}


@NodeEntity
data class Metric(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = "",
        @Relationship(type = "CALCULATES", direction = Relationship.OUTGOING)
        var calculus: Calculus? = null
) : Measurable {
    fun calculates(calculus: Calculus) = run { this.calculus = calculus }
    fun calculate() = calculus?.calculate()

    override fun measure() {
        // TODO should save the measurement
        val value = calculate()
    }
}


@NodeEntity
data class Calculus(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = "",
        var value: Double? = null,
        var left: Calculable? = null,
        var right: Calculable? = null,
        var operator: Operator? = null
) : Calculable {
    override fun calculate(): Double {
        return if (operator != null && left != null && right != null)
            operator!!.apply(left!!.calculate(), right!!.calculate())
        else if (value != null) value!!
        else -1.0
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
