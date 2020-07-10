package com.calculator.model

import org.neo4j.ogm.annotation.*


@NodeEntity
data class MeasurableMetric(
        @Id @GeneratedValue
        val id: Long = -1,
        @Relationship(type = "HAS_A", direction = Relationship.OUTGOING)
        val attributes: MutableSet<Attribute> = mutableSetOf()

)


@NodeEntity
data class Attribute(
        @Id @GeneratedValue val id: Long = -1,
        val name: String = "",
        val description: String = ""
)
