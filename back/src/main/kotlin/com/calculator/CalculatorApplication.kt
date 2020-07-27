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
			metricSetRepository: MetricSetRepository,
			attributeRepository: AttributeRepository,
			metricRepository: MetricRepository,
			calculusRepository: CalculusRepository
	): CommandLineRunner? {
		return CommandLineRunner { args: Array<String?>? ->
			metricSetRepository.deleteAll()
			attributeRepository.deleteAll()
			metricRepository.deleteAll()
			calculusRepository.deleteAll()

			val measurable1 = MetricSet(
					name = "1",
					description = "this node is measurable"
			)
			metricSetRepository.save(measurable1)

			val measurable2 = MetricSet(
					name = "2",
					description = "this node is measurable"
			)
			metricSetRepository.save(measurable2)

			val measurable3 = MetricSet(
					name = "3",
					description = "this node is measurable"
			)
			metricSetRepository.save(measurable3)

			measurable1.measures(measurable2)
			metricSetRepository.save(measurable1)
			measurable2.measures(measurable3)
			metricSetRepository.save(measurable2)

			val parent = Attribute(
					name = "parent",
					description = "this node has children"
			)
			attributeRepository.save(parent)

			measurable1.hasAttribute(parent)
			measurable2.hasAttribute(parent)
			metricSetRepository.saveAll(setOf(measurable1, measurable2))


			val five = Calculus(
					name = "5",
					description = "the number 5",
					value = 5.0
			)
			calculusRepository.save(five)

			val two = Calculus(
					name = "2",
					description = "the number 2",
					value = 2.0
			)
			calculusRepository.save(two)

			val fiveTimesTwo = Calculus(
					name = "5 * 2",
					description = "a multiplication",
					operator = Operator.TIMES,
					left = five,
					right = two
			)
			calculusRepository.save(fiveTimesTwo)

			println("5 * 2 = ${fiveTimesTwo.calculate()}")
		}

	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}

