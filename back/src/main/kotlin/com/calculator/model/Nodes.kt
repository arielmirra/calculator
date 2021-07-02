package com.calculator.model

import org.springframework.data.neo4j.core.schema.*
import java.time.LocalDateTime
import java.util.function.BinaryOperator
import java.util.function.DoubleBinaryOperator

@Node
data class Company(
    @Id @GeneratedValue val id: Long = -1,
    var name: String = "",
    var description: String = "",
    @Relationship(type = "projects")
    var projects: MutableSet<Project> = mutableSetOf()
)

@Node
data class Project(
    @Id @GeneratedValue val id: Long = -1,
    var name: String = "",
    var description: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    @Relationship(type = "metrics")
    var calculables: MutableSet<Calculable> = mutableSetOf()
)


@Node
data class Calculable(
    @Id @GeneratedValue val id: Long = -1,
    var name: String = "",
    var description: String = "",
    var calculableType: CalculableType = CalculableType.VARIABLE,
    @Relationship(type = "left")
    var left: Calculable? = null,
    @Relationship(type = "right")
    var right: Calculable? = null,
    var operator: Operator? = null,
    var value: Double? = null,
    var lastMeasured: LocalDateTime = LocalDateTime.now(),
) {
    fun calculate(): Double = value ?: operator!!.apply(left!!.calculate(), right!!.calculate())

    fun deepCopy(): Calculable {
        return if (operator != null) {
            Calculable(
                name = name,
                description = description,
                calculableType = CalculableType.COPY,
                operator = operator,
                left = left!!.deepCopy(),
                right = right!!.deepCopy()
            )
        } else {
            Calculable(
                name = name,
                description = description,
                calculableType = CalculableType.COPY,
                value = value
            )
        }
    }
}

enum class CalculableType {
    METRIC, VARIABLE, COPY
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
data class Formula(
    @Id @GeneratedValue val id: Long = -1,
    var calcTree: Calculable,
    @CompositeProperty var variables: MutableMap<String, Double> = mutableMapOf(),
    @Relationship(type = "from")
    val fromId: Long,
)

@Node
data class Measurement(
    @Id @GeneratedValue val id: Long = -1,
    val value: Double,
    val date: LocalDateTime = LocalDateTime.now(),
    @Relationship(type = "from")
    val from: Formula,
)
