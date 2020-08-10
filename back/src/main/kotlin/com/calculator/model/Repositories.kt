package com.calculator.model

import com.calculator.model.*
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Repository
interface MetricSetRepository : Neo4jRepository<MetricSet, Long>{
    fun findByName(name: String): MetricSet?

    @Query("match (m:MetricSet)-[*]->(mm:Metric)-[*]->(c:Calculus)-[*]->(cc:Value) return m,mm,c,cc")
    fun getFullMetrics(): List<MetricSet>
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

    @Query("")
    fun getCalcByName(name: String): Calculus?
}

@Repository
interface ValueRepository : Neo4jRepository<Value, Long>{
    fun findByName(name: String): Value?
}

@Repository
interface MeasurementRepository : Neo4jRepository<Measurement, Long>{
    fun findByName(name: String): Measurement?
}
