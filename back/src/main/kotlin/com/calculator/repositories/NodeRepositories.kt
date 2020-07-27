package com.calculator.repositories

import com.calculator.model.*
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository


@Repository
interface MeasurableRepository : Neo4jRepository<Measurable, Long>{
    fun findByName(name: String): Measurable?
}

@Repository
interface AttributeRepository : Neo4jRepository<Attribute, Long>{
    fun findByName(name: String): Attribute?
}

@Repository
interface MetricRepository : Neo4jRepository<Metric, Long>{
    fun findByName(name: String): Metric?
}

@Repository
interface CalculableRepository : Neo4jRepository<Calculable, Long>{
    fun findByName(name: String): Calculable?
}
