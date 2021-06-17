package com.calculator.model

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.stereotype.Repository

@Repository
interface MeasurementRepository : Neo4jRepository<Measurement, Long> {
//    fun findByName(name: String): Measurement?
//    fun findAllByMetricId(id: Long): List<Measurement>
}

@Repository
interface CalculableRepository : Neo4jRepository<Calculable, Long> {
    fun findByName(name: String): Calculable?

    @Query(
        "MATCH path = (n:Metric)-[*]-(c:Calculable)\n" +
                "WHERE ID(c) = {0}\n" +
                "WITH collect(path) as paths\n" +
                "CALL apoc.convert.toTree(paths) yield value\n" +
                "RETURN value;"
    )
    fun calcUsage(id: Long): List<Any>
}

@Repository
interface MetricRepository : Neo4jRepository<Metric, Long> {
    fun findByName(name: String): Metric?
}

@Repository
interface ProjectRepository : Neo4jRepository<Project, Long> {
    fun findByName(name: String): Project?
}

@Repository
interface CompanyRepository : Neo4jRepository<Company, Long> {
    fun findByName(name: String): Company?
}

@Repository
interface FormulaRepository : Neo4jRepository<Formula, Long> {
    fun findAllByCalcTreeId(id: Long): List<Formula>
}
