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
            metricRepository: MetricRepository,
            calculableRepository: CalculableRepository
    ): CommandLineRunner? {
        return CommandLineRunner {
            metricRepository.deleteAll()
            calculableRepository.deleteAll()

            // calculus
            val five = calculableRepository.save(Calculable(
                    name = "5",
                    value = 5.0
            ))

            val two = calculableRepository.save(Calculable(
                    name = "2",
                    value = 2.0
            ))

            val fiveTimesTwo = calculableRepository.save(Calculable(
                    name = "5 * 2",
                    operator = Operator.TIMES,
                    left = five,
                    right = two
            ))

            val fromRepository = calculableRepository.findByName("5 * 2")!!
            println("---")
            println(fiveTimesTwo)
            println(fromRepository) // sometimes has different id than fiveTimesTwo
            println("5 * 2 = ${fromRepository.calculate()}")
            println("--- \n")


            // metric
            val metric = metricRepository.save(Metric(
                    name = "Metric 1",
                    description = "Measures a calculus",
                    calculates = mutableSetOf(fromRepository)
            ))
            metricRepository.save(metric)

            val metricFromRepository = metricRepository.findByName("Metric 1")!!
            println("---")
            println(metric)
            println(metricFromRepository) // calculates node is partially empty
            println("--- \n")

            println("---")
            println(metric.measure())
            println("--- \n")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CalculatorApplication>(*args)
}
