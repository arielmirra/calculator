package com.calculator.model

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
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
        @Relationship(type = "HAS_ATTRIBUTE")
        val attributes: MutableSet<Attribute> = mutableSetOf(),
        @Relationship(type = "MEASURES")
        val metrics: MutableSet<Measurable> = mutableSetOf()

) : Measurable {
    fun hasAttribute(attribute: Attribute) = attributes.add(attribute)
    fun measures(measurable: Measurable) = metrics.add(measurable)

    override fun measure(): Measurement {
        // TODO should create unique exception?
        if (metrics.isEmpty()) throw NullPointerException("there are no metrics to measure")
        // TODO should save the measurement
        val result = metrics.map { m -> m.measure().value }.sum()
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
        @Relationship(type = "CALCULATES")
        val calculates: MutableSet<Calculable> = mutableSetOf()
) : Measurable {
    fun calculates(calculable: Calculus) = calculates.add(calculable)
    fun calculate() = calculates.map { c -> c.calculate() }.sum()

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
        @Relationship(type = "LEFT")
        var left: Calculable? = null,
        @Relationship(type = "RIGHT")
        var right: Calculable? = null,
        var operator: Operator? = null
) : Calculable {
    override fun calculate(): Double =
            operator!!.apply(left!!.calculate(), right!!.calculate())
}

@NodeEntity
data class Value(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        var value: Double = 0.0
) : Calculable {
    override fun calculate(): Double = value
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
