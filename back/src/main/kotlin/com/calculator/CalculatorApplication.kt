package com.calculator

import com.calculator.model.Person
import com.calculator.repositories.PersonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories


@SpringBootApplication
@EnableNeo4jRepositories
class CalculatorApplication {
	@Bean
	fun demo(personRepository: PersonRepository): CommandLineRunner? {
		return CommandLineRunner { args: Array<String?>? ->
			personRepository.deleteAll()

			var greg = Person("Greg")
			var roy = Person("Roy")
			val craig = Person("Craig")
			val team: List<Person> = listOf(greg,craig,roy)

			println("Before linking up with Neo4j...")
			team.stream().forEach { person: Person -> println("\t" + person.toString()) }
			personRepository.save(greg)
			personRepository.save(roy)
			personRepository.save(craig)

			greg = greg.getName()?.let { personRepository.findByName(it) }!!
			greg.worksWith(roy)
			greg.worksWith(craig)
			personRepository.save(greg)
			roy = roy.getName()?.let { personRepository.findByName(it) }!!
			roy.worksWith(craig)

			personRepository.save(roy)

			println("Lookup each person by name...")
			team.stream().forEach { person: Person ->
				println(
						"\t" + person.getName()?.let { personRepository.findByName(it).toString() })
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}

