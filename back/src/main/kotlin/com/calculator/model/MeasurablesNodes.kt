package com.calculator.model

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.function.BinaryOperator
import java.util.function.DoubleBinaryOperator


@NodeEntity
data class Measurable(
        @Id @GeneratedValue
        val id: Long = -1,
        val name: String = "",
        val description: String = "",
        @Relationship(type = "HAS_ATTRIBUTE", direction = Relationship.OUTGOING)
        val attributes: MutableSet<Attribute> = mutableSetOf(),
        @Relationship(type = "HAS_CHILDREN", direction = Relationship.OUTGOING)
        val children: MutableSet<Measurable> = mutableSetOf(),
        @Relationship(type = "MEASURES", direction = Relationship.OUTGOING)
        val metrics: MutableSet<Metric> = mutableSetOf()

) {
    fun hasAttribute(attribute: Attribute) = attributes.add(attribute)
    fun hasChildren(measurable: Measurable) = children.add(measurable)
    fun measures(metric: Metric) = metrics.add(metric)
}


@NodeEntity
data class Attribute(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = ""
)


@NodeEntity
data class Metric(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = "",
        @Relationship(type = "CALCULATES", direction = Relationship.OUTGOING)
        var calculus: Calculus? = null
) {
    fun calculates(calculus: Calculus) = run { this.calculus = calculus }
    fun calculate() = calculus?.calculate()
}

interface Calculus {
    fun calculate(): Double
}


@NodeEntity
data class Calculable(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = "",
        var value: Double? = null,
        var left: Calculable? = null,
        var right: Calculable? = null,
        var operator: Operator? = null
) : Calculus {
    override fun calculate(): Double {
        return if (operator != null && left != null && right != null)
            operator!!.apply(left!!.calculate(), right!!.calculate())
        else  if(value != null) value!!
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
