package com.calculator.model

import com.calculator.model.*
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository


@Repository
interface MetricSetRepository : Neo4jRepository<MetricSet, Long>{
    fun findByName(name: String): MetricSet?
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
interface CalculusRepository : Neo4jRepository<Calculus, Long>{
    fun findByName(name: String): Calculus?
}

@Repository
interface MeasurementRepository : Neo4jRepository<Measurement, Long>{
    fun findByName(name: String): Measurement?
}
