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
            companyRepository: CompanyRepository,
            projectRepository: ProjectRepository,
            metricRepository: MetricRepository,
            calculableRepository: CalculableRepository,
            measurementRepository: MeasurementRepository
    ): CommandLineRunner? {
        return CommandLineRunner {
            companyRepository.deleteAll()
            projectRepository.deleteAll()
            metricRepository.deleteAll()
            calculableRepository.deleteAll()
            measurementRepository.deleteAll()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CalculatorApplication>(*args)
}
