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
			greg = personRepository.findByName(greg.getName())!!
			greg.worksWith(roy)
			greg.worksWith(craig)
			personRepository.save(greg)
			roy = personRepository.findByName(roy.getName())!!
			roy.worksWith(craig)
			// We already know that roy works with greg
			personRepository.save(roy)

			// We already know craig works with roy and greg
			println("Lookup each person by name...")
			team.stream().forEach { person: Person ->
				println(
						"\t" + personRepository.findByName(person.getName()).toString())
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}

