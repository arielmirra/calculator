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
        calculableRepository: CalculableRepository,
        measurementRepository: MeasurementRepository,
        formulaRepository: FormulaRepository
    ): CommandLineRunner? {
        return CommandLineRunner {
            companyRepository.deleteAll()
            projectRepository.deleteAll()
            calculableRepository.deleteAll()
            measurementRepository.deleteAll()
            formulaRepository.deleteAll()
        }
    }

    @Bean
    fun testSetup(
        companyRepository: CompanyRepository,
        projectRepository: ProjectRepository,
        calculableRepository: CalculableRepository,
        measurementRepository: MeasurementRepository,
        formulaRepository: FormulaRepository
    ): CommandLineRunner? {
        return CommandLineRunner {
            companyRepository.deleteAll()
            projectRepository.deleteAll()
            calculableRepository.deleteAll()
            measurementRepository.deleteAll()
            formulaRepository.deleteAll()

            val simpleCalc = calculableRepository.save(Calculable(
                name = "X",
                calculableType = CalculableType.VARIABLE,
            ))

            val simpleCalc2 = calculableRepository.save(Calculable(
                name = "Y",
                calculableType = CalculableType.VARIABLE,
            ))

            val complexCalc = calculableRepository.save(Calculable(
                name = "variable compuesta",
                left = simpleCalc,
                right = simpleCalc2,
                operator = Operator.PLUS
            ))

//            val testMetric = metricRepository.save(Metric(
//                name = "test",
//                description = "test metric",
//                calculates = mutableSetOf(simpleCalc, simpleCalc2)
//            ))
//
//            val emptyMetric = metricRepository.save(Metric(
//                name = "empty",
//                description = "empty"
//            ))
//
//            val complexMetric = metricRepository.save(Metric(
//                name = "complexMetric",
//                description = "complex metric",
//                calculates = mutableSetOf(complexCalc),
//                metrics = mutableSetOf(emptyMetric)
//            ))

            val emptyProject = projectRepository.save(Project(
                name = "Proyecto vacío",
                description = "empty project"
            ))

            val project = projectRepository.save(Project(
                name = "proyecto normal",
                description = "proyecto con una métrica",
                calculables = mutableSetOf(complexCalc)
            ))

            val emptyCompany = companyRepository.save(Company(
                name = "empresa vacía",
                description = "compañía vacía"
            ))

            val company = companyRepository.save(Company(
                name = "Empresa normal",
                description = "Empresa con un proyecto dentro",
                projects = mutableSetOf(project)
            ))

//            val measurement = measurementRepository.save(Measurement(
//                name = "test measurement",
//                value = 1.0,
//                from = testMetric,
//                metricId = testMetric.id
//            ))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CalculatorApplication>(*args)
}
