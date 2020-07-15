package com.calculator

import com.calculator.model.Attribute
import com.calculator.model.Calculable
import com.calculator.model.Measurable
import com.calculator.model.Person
import com.calculator.repositories.*
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories


@SpringBootApplication
@EnableNeo4jRepositories
class CalculatorApplication {


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

	@Bean
	fun setupDemo(
			measurableRepository: MeasurableRepository,
			attributeRepository: AttributeRepository,
			metricRepository: MetricRepository,
			calculableRepository: CalculableRepository
	): CommandLineRunner? {
		return CommandLineRunner { args: Array<String?>? ->
			measurableRepository.deleteAll()
			attributeRepository.deleteAll()
			metricRepository.deleteAll()
			calculableRepository.deleteAll()

			var measurable1 = Measurable(
					name = "1",
					description = "this node is measurable"
			)
			measurableRepository.save(measurable1)

			var measurable2 = Measurable(
					name = "2",
					description = "this node is measurable"
			)
			measurableRepository.save(measurable2)

			var measurable3 = Measurable(
					name = "3",
					description = "this node is measurable"
			)
			measurableRepository.save(measurable3)

			measurable1.hasChildren(measurable2)
			measurableRepository.save(measurable1)
			measurable2.hasChildren(measurable3)
			measurableRepository.save(measurable2)

			val parent = Attribute(
					name = "parent",
					description = "this node has children"
			)
			attributeRepository.save(parent)

			measurable1.hasAttribute(parent)
			measurable2.hasAttribute(parent)
			measurableRepository.saveAll(setOf(measurable1, measurable2))


			var five = Calculable(
					name = "5",
					description = "the number 5"
			)

			var two = Calculable(
					name = "2",
					description = "the number 2"
			)

			var fiveTimesTwo = Calculable(
					name = "2",
					description = "the number 2"
			)
		}

	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}

