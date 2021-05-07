package com.calculator.model

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.LocalDateTime
import java.util.function.BinaryOperator
import java.util.function.DoubleBinaryOperator

@Node
data class Company(
    @Id @GeneratedValue val _id: Long = -1,
    val _type: String = "Company",
    var name: String = "",
    var description: String = "",
    @Relationship(type = "projects")
    var projects: MutableSet<Project> = mutableSetOf()
)

@Node
data class Project(
    @Id @GeneratedValue val _id: Long = -1,
    val _type: String = "Project",
    var name: String = "",
    var description: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    @Relationship(type = "metrics")
    var metrics: MutableSet<Metric> = mutableSetOf()
)


@Node
data class Metric(
    @Id @GeneratedValue val _id: Long = -1,
    val _type: String = "Metric",
    var name: String = "",
    var description: String = "",
    @Relationship(type = "has_metrics")
    var metrics: MutableSet<Metric> = mutableSetOf(),
    @Relationship(type = "calculates")
    var calculates: MutableSet<Calculable> = mutableSetOf()
) {
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


@Node
data class Calculable(
    @Id @GeneratedValue val _id: Long = -1,
    val _type: String = "Calculable",
    var name: String = "",
    @Relationship(type = "left")
    var left: Calculable? = null,
    @Relationship(type = "right")
    var right: Calculable? = null,
    var operator: Operator? = null,
    var value: Double? = null
) {
    fun calculate(): Double = value ?: operator!!.apply(left!!.calculate(), right!!.calculate())
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

@Node
data class Measurement(
    @Id @GeneratedValue val _id: Long = -1,
    val _type: String = "Measurement",
    var name: String = "",
    val value: Double = 0.0,
    val date: LocalDateTime = LocalDateTime.now()
)
