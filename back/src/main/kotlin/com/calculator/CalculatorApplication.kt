package com.calculator

import com.calculator.model.*
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
			calculusRepository: CalculusRepository,
			measurementRepository: MeasurementRepository
	): CommandLineRunner? {
		return CommandLineRunner { args: Array<String?>? ->
			metricSetRepository.deleteAll()
			attributeRepository.deleteAll()
			metricRepository.deleteAll()
			calculusRepository.deleteAll()
			measurementRepository.deleteAll()

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

			val metric1 = Metric(
					name = "Metric 1",
					description = "Measures a calculus",
					calculus = fiveTimesTwo
			)
			metricRepository.save(metric1)

			val measurable1 = MetricSet(
					name = "MetricSet 1",
					description = "this node is measurable"
			)
			metricSetRepository.save(measurable1)

			val measurable2 = MetricSet(
					name = "MetricSet 2",
					description = "this node is measurable"
			)
			metricSetRepository.save(measurable2)

			val measurable3 = MetricSet(
					name = "MetricSet 3",
					description = "this node is measurable",
					metrics = mutableSetOf(metric1)
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

			println("MetricSet1 Measure Result: ${measurable1.measure()}")
		}

	}
}

fun main(args: Array<String>) {
	runApplication<CalculatorApplication>(*args)
}

