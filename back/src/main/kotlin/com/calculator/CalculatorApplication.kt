package com.calculator

import com.calculator.model.*
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement
class CalculatorApplication {

    @Bean
    fun demo(
            metricSetRepository: MetricSetRepository,
            attributeRepository: AttributeRepository,
            metricRepository: MetricRepository,
            calculusRepository: CalculusRepository,
            valueRepository: ValueRepository,
            measurementRepository: MeasurementRepository
    ): CommandLineRunner? {
        return CommandLineRunner { args: Array<String?>? ->
            metricSetRepository.deleteAll()
            attributeRepository.deleteAll()
            metricRepository.deleteAll()
            calculusRepository.deleteAll()
            valueRepository.deleteAll()
            measurementRepository.deleteAll()


            // calculus
            val five = valueRepository.save(Value(
                    name = "5",
                    value = 5.0
            ))

            val two = valueRepository.save(Value(
                    name = "2",
                    value = 2.0
            ))

            val fiveTimesTwo = calculusRepository.save(Calculus(
                    name = "5 * 2",
                    operator = Operator.TIMES,
                    left = five,
                    right = two
            ))
            println("---")
            println(fiveTimesTwo)
            val calc = calculusRepository.findByName("5 * 2")!!
            println(calc)
            println("5 * 2 = ${calc.calculate()}")
            println("--- \n")


            // metric
            val metric = metricRepository.save(Metric(
                    name = "Metric 1",
                    description = "Measures a calculus"
            ))

            metric.calculates(calc)
            metricRepository.save(metric)

            println("---")
            println(metric)
            val metric2 = metricRepository.findByName("Metric 1")!!
            println(metric2)
            println("--- \n")

            // metric sets
            var set1 = metricSetRepository.save(MetricSet(
                    name = "MetricSet 1",
                    description = "this node is measurable"
            ))

            var set2 = metricSetRepository.save(MetricSet(
                    name = "MetricSet 2",
                    description = "this node is measurable"
            ))

            var set3 = metricSetRepository.save(MetricSet(
                    name = "MetricSet 3",
                    description = "this node is measurable",
                    metrics = mutableSetOf(metric)
            ))

//            val parent = Attribute(
//                    name = "parent",
//                    description = "this node has children"
//            )
//            attributeRepository.save(parent)
//
//            set1.hasAttribute(parent)
//            set2.hasAttribute(parent)
            set1.measures(set2)
            set2.measures(set3)
            metricSetRepository.saveAll(setOf(set1, set2))

            println("---")
            println("MetricSet1 Measure Result: ${set1.measure()}")
            val set1Bis = metricSetRepository.findByName("MetricSet 1")!!
            println(set1)
            println(set1Bis)
            println("--- \n")
        }

    }
}

fun main(args: Array<String>) {
    runApplication<CalculatorApplication>(*args)
}
