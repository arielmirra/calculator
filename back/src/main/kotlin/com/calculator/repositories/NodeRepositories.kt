package com.calculator.repositories

import com.calculator.model.Attribute
import com.calculator.model.Measurable
import com.calculator.model.Person
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.stereotype.Repository


@Repository
interface MeasurableRepository : Neo4jRepository<Measurable, Long>

@Repository
interface AttributeRepository : Neo4jRepository<Attribute, Long>

@Repository
interface PersonRepository : Neo4jRepository<Person, Long> {
    fun findByName(name: String?): Person?
}
