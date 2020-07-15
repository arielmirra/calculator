package com.calculator.model

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship
import java.util.*
import java.util.stream.Collectors


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
        var formula: Calculable? = null
){
        fun calculates(calculable: Calculable) = run { formula = calculable }
}

interface Calculus {
        fun calculate(): Number
}


@NodeEntity
data class Calculable(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = "",
        val value: Number = 0,
        @Relationship(type = "CALCULATES", direction = Relationship.OUTGOING)
        var formula: Calculable? = null
): Calculus {
        fun calculates(calculable: Calculable) = run { formula = calculable }
        override fun calculate() = value
}


// just for testing
@NodeEntity
class Person {
        @Id
        @GeneratedValue
        private val id: Long? = null
        private var name: String? = null

        private constructor() {
        }

        constructor(name: String?) {
                this.name = name
        }

        @Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
        var teammates: MutableSet<Person>? = null
        fun worksWith(person: Person) {
                if (teammates == null) {
                        teammates = HashSet()
                }
                teammates!!.add(person)
        }

        override fun toString(): String {
                return ("$name's teammates => "
                        + Optional.ofNullable(teammates).orElse(
                        Collections.emptySet()).stream()
                        .map { obj: Person -> obj.getName() }
                        .collect(Collectors.toList()))
        }

        fun getName(): String? {
                return name
        }

        fun setName(name: String?) {
                this.name = name
        }
}
