package com.calculator.model

import org.springframework.data.neo4j.annotation.Depth
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository


@Repository
interface MetricRepository : Neo4jRepository<Metric, Long>{
    @Depth(4)
    fun findByName(name: String): Metric?

//    fun findByName(name: String, @Depth depth: Int): Metric?
}

@Repository
interface CalculableRepository : Neo4jRepository<Calculable, Long>{
    @Depth(4)
    fun findByName(name: String): Calculable?

//    fun findByName(name: String, @Depth depth: Int): Calculable?
}
