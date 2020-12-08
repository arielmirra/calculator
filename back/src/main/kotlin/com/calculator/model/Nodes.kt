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
data class Attribute(
    @Id @GeneratedValue val id: Long = -1,
    var name: String = "",
    val description: String = ""
)


@NodeEntity
data class Metric(
    @Id @GeneratedValue override val _id: Long = -1,
    override val _type: String = "Metric",
    var name: String = "",
    var description: String = "",
    @Relationship(type = "HAS_ATTRIBUTE")
    var attributes: MutableSet<Attribute> = mutableSetOf(),
    @Relationship(type = "MEASURES")
    var metrics: MutableSet<Metric> = mutableSetOf(),
    @Relationship(type = "CALCULATES")
    var calculates: MutableSet<Calculable> = mutableSetOf()
) : Node {
    fun hasAttribute(attribute: Attribute) = attributes.add(attribute)
    fun measures(metric: Metric) = metrics.add(metric)

    fun measure(): Measurement {
        // TODO should save the measurement

        var result = if (metrics.isEmpty()) 0.0 else metrics.map { m -> m.measure().value }.sum()

        if (calculates.isNotEmpty()) result += calculate()

        return Measurement(
            name = "$name metric measured",
            value = result
        )
    }

    fun calculates(calculable: Calculable) = calculates.add(calculable)

    private fun calculate(): Double {
        return if (calculates.isNotEmpty()) calculates.map { c -> c.calculate() }.sum()
        else 0.0
    }
}


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
