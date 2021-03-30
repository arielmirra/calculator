package com.calculator.model

import org.springframework.data.neo4j.annotation.Depth
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MeasurementRepository : Neo4jRepository<Measurement, Long> {
    @Depth(5)
    fun findByName(name: String): Measurement?

    @Depth(5)
    fun findBy_id(_id: Long): Optional<Measurement>
}

@Repository
interface CalculableRepository : Neo4jRepository<Calculable, Long> {
    @Depth(5)
    fun findByName(name: String): Calculable?

    @Depth(5)
    fun findBy_id(_id: Long): Optional<Calculable>

    @Query(
        "MATCH path = (n:Metric)-[*]-(c:Calculable)\n" +
                "WHERE ID(c) = {0}\n" +
                "WITH collect(path) as paths\n" +
                "CALL apoc.convert.toTree(paths) yield value\n" +
                "RETURN value;"
    )
    fun calcUsage(_id: Long): List<Any>
}

@Repository
interface MetricRepository : Neo4jRepository<Metric, Long> {
    @Depth(5)
    fun findBy_id(_id: Long): Optional<Metric>

    @Depth(5)
    fun findByName(name: String): Metric?

//    @Query("MATCH path = (n:Metric{name: :`:#{literal(#name)}`})-[*]-(c) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
//    fun findByName(name: String): Metric? = test(java.lang.String(name))

//    @Query("MATCH path = (n:Metric{name: :\$name})-[*]-(c) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
//    fun test(name: java.lang.String): Metric?

    @Query("MATCH path = (n:Metric)-[*]-(c: Calculable) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
    fun fetchAllComplete(): List<Any>

    @Query("MATCH path = (n:Metric) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
    fun fetchAll(): List<Any>
}

@Repository
interface ProjectRepository : Neo4jRepository<Project, Long> {
    @Depth(5)
    fun findByName(name: String): Project?

    @Depth(5)
    fun findBy_id(_id: Long): Optional<Project>

    @Query("MATCH path = (n:Project)-[*]-(m) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
    fun fetchAll(): List<Any>
}

@Repository
interface CompanyRepository : Neo4jRepository<Company, Long> {
    @Depth(5)
    fun findByName(name: String): Company?

    @Depth(5)
    fun findBy_id(_id: Long): Optional<Company>

    @Query("MATCH path = (n:Company)-[*]-(p) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
    fun fetchAll(): List<Any>
}
