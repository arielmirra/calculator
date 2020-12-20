package com.calculator.model

import org.springframework.data.neo4j.annotation.Depth
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface MetricRepository : Neo4jRepository<Metric, Long> {
    @Depth(4)
    fun findBy_id(_id: Long): Optional<Metric>

    @Depth(4)
    fun findByName(name: String): Metric?

    @Query("MATCH path = (n:Metric)-[*]-(c: Calculable) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
    fun fetchAllComplete(): List<Any>

    @Query("MATCH path = (n:Metric) WITH collect(path) as paths CALL apoc.convert.toTree(paths) yield value RETURN value;")
    fun fetchAll(): List<Any>
}

@Repository
interface CalculableRepository : Neo4jRepository<Calculable, Long> {
    @Depth(4)
    fun findByName(name: String): Calculable?

    @Depth(4)
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
