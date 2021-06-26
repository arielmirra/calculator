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

            val x = calculableRepository.save(Calculable(
                name = "X",
                calculableType = CalculableType.VARIABLE,
            ))

            val y = calculableRepository.save(Calculable(
                name = "Y",
                calculableType = CalculableType.VARIABLE,
            ))

            val z = calculableRepository.save(Calculable(
                name = "Z",
                calculableType = CalculableType.VARIABLE,
            ))

            val metric1 = calculableRepository.save(Calculable(
                name = "Métrica común",
                description = "calcula el resultado de X + Y",
                left = x,
                right = y,
                operator = Operator.PLUS,
                calculableType = CalculableType.METRIC
            ))

            val metric2 = calculableRepository.save(Calculable(
                name = "Métrica compuesta",
                description = "calcula el resultado de (X + Y) * Z",
                left = metric1,
                right = z,
                operator = Operator.TIMES,
                calculableType = CalculableType.METRIC
            ))

            val emptyProject = projectRepository.save(Project(
                name = "Proyecto vacío",
                description = "empty project"
            ))

            val project = projectRepository.save(Project(
                name = "proyecto común",
                description = "proyecto con una métrica",
                calculables = mutableSetOf(metric1)
            ))

            val project2 = projectRepository.save(Project(
                name = "proyecto compuesto",
                description = "proyecto con varias métricas",
                calculables = mutableSetOf(metric1, metric2)
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
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CalculatorApplication>(*args)
}
