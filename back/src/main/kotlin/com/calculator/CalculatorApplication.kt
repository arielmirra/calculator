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

//    @Bean
    fun setup(
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

    @Bean
    fun testSetup(
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

            val simpleCalc = calculableRepository.save(Calculable(
                name = "1",
                value = 1.0
            ))

            val simpleCalc2 = calculableRepository.save(Calculable(
                name = "2",
                value = 2.0
            ))

            val complexCalc = calculableRepository.save(Calculable(
                name = "3",
                left = simpleCalc,
                right = simpleCalc2,
                operator = Operator.PLUS
            ))

            val metric = metricRepository.save(Metric(
                name = "test",
                description = "test metric",
                calculates = mutableSetOf(simpleCalc, simpleCalc2)
            ))

            val emptyMetric = metricRepository.save(Metric(
                name = "empty",
                description = "empty"
            ))

            val complexMetric = metricRepository.save(Metric(
                name = "complexMetric",
                description = "complex metric",
                calculates = mutableSetOf(complexCalc),
                metrics = mutableSetOf(emptyMetric)
            ))

            val emptyProject = projectRepository.save(Project(
                name = "emptyProject",
                description = "empty project"
            ))

            val project = projectRepository.save(Project(
                name = "project",
                description = "project with a metric",
                metrics = mutableSetOf(complexMetric)
            ))

            val emptyCompany = companyRepository.save(Company(
                name = "emptyCompany",
                description = "empty company"
            ))

            val company = companyRepository.save(Company(
                name = "company",
                description = "company with a project",
                projects = mutableSetOf(project)
            ))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CalculatorApplication>(*args)
}
