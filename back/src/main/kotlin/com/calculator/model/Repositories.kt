package com.calculator.model

import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository

@Repository
interface MeasurementRepository : Neo4jRepository<Measurement, Long> {
    fun findAllByFrom_FromId(id: Long): List<Measurement>
}

@Repository
interface CalculableRepository : Neo4jRepository<Calculable, Long> {
    fun findByName(name: String): Calculable?
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
