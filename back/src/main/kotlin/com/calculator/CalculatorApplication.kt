package com.calculator

import com.calculator.model.*
import com.calculator.repositories.*
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories


@SpringBootApplication
@EnableNeo4jRepositories
class CalculatorApplication {

	@Bean
	fun demo(
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

			val measurable1 = Measurable(
					name = "1",
					description = "this node is measurable"
			)
			measurableRepository.save(measurable1)

			val measurable2 = Measurable(
					name = "2",
					description = "this node is measurable"
			)
			measurableRepository.save(measurable2)

			val measurable3 = Measurable(
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


			val five = Calculable(
					name = "5",
					description = "the number 5",
					value = 5.0
			)
			calculableRepository.save(five)

			val two = Calculable(
					name = "2",
					description = "the number 2",
					value = 2.0
			)
			calculableRepository.save(two)

			val fiveTimesTwo = Calculable(
					name = "5 * 2",
					description = "a multiplication",
					operator = Operator.TIMES,
					left = five,
					right = two
			)
			calculableRepository.save(fiveTimesTwo)

			println("5 * 2 = ${fiveTimesTwo.calculate()}")
		}

	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}

