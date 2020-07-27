package com.calculator.model

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.lang.NullPointerException
import java.time.Instant
import java.util.*
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
        val metrics: MutableSet<Measurable> = mutableSetOf()

) : Measurable {
    fun hasAttribute(attribute: Attribute) = attributes.add(attribute)
    fun measures(measurable: Measurable) = metrics.add(measurable)

    override fun measure(): Measurement {
        // TODO should create unique exception?
        if (metrics.isEmpty()) throw NullPointerException("there are no metrics to measure")
        // TODO should save the measurement
        val result = metrics.map{m -> m.measure().value}.sum()
        return Measurement(
                name = "$name measured",
                value = result
        )
    }
}


@NodeEntity
data class Metric(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = "",
        @Relationship(type = "CALCULATES", direction = Relationship.OUTGOING)
        var calculus: Calculus
) : Measurable {
    fun calculates(calculus: Calculus) = run { this.calculus = calculus }
    fun calculate() = calculus.calculate()

    override fun measure(): Measurement {
        // TODO should save the measurement
        val result = calculate()
        return Measurement(
                name = "$name measured",
                value = result
        )
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

@NodeEntity
data class Measurement(
        @Id @GeneratedValue val id: Long = -1,
        val name: String,
        val value: Double,
        val date: Date = Date.from(Instant.now())!!
)
