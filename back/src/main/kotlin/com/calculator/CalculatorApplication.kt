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


            // FunctionalSuitability
            val FunctionalCoverage = metricRepository.save(
                Metric(
                    name = "is the metric of how much the specified and designed functionalities have been implemented and covered by the testbench. This measure applies to the assessment of interfacing practices, contributing to determine the percentage of specified functions that the interface implements.",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.FUNCTIONAL_SUITABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.FUNCTIONAL_COMPLETENESS
                )
            )

            val FunctionalCorrectness = metricRepository.save(
                Metric(
                    name = "It refers to the input-output behavior of an algorithm or system, i.e., the expected output produced by the system for a specific input",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.FUNCTIONAL_SUITABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.FUNCTIONAL_CORRECTNESS
                )
            )

            val FunctionalAppropriatenessOfUsageObjective = metricRepository.save(
                Metric(
                    name = "Functional appropriateness of usage objective",
                    description = " refers to what portion\n" +
                            "of the functions required by the user provides the appropriate\n" +
                            "outcome to achieve a specific usage objective",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.FUNCTIONAL_SUITABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.FUNCTIONAL_APPROPRIATENESS
                )
            )

            val FunctionalAppropriatenessOfTheSystem = metricRepository.save(
                Metric(
                    name = "Functional appropriateness of the system",
                    description = "refers to what\n" +
                            "proportion of the functions required by the users to achieve\n" +
                            "their objectives provides an appropriate outcome",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.FUNCTIONAL_SUITABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.FUNCTIONAL_APPROPRIATENESS
                )
            )

            val FunctionalSuitability = metricRepository.save(
                Metric(
                    name = "QA Functional appropriateness of the system",
                    metrics = mutableSetOf(
                        FunctionalCoverage,
                        FunctionalCorrectness,
                        FunctionalAppropriatenessOfUsageObjective,
                        FunctionalAppropriatenessOfTheSystem
                    )
                )
            )



            // Performance Efficiency
            val MeanResponseTime = metricRepository.save(
                Metric(
                    name = "Mean response time",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.TIME_BEHAVIOR
                )
            )

            val ResponseTimeAdequacy = metricRepository.save(
                Metric(
                    name = "Response time adequacy",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.TIME_BEHAVIOR
                )
            )

            val MeanTurnaroundTime = metricRepository.save(
                Metric(
                    name = "Mean turnaround time",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.TIME_BEHAVIOR
                )
            )

            val TurnaroundTimeAdequacy = metricRepository.save(
                Metric(
                    name = "Turnaround time adequacy",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.TIME_BEHAVIOR
                )
            )

            val MeanThroughput = metricRepository.save(
                Metric(
                    name = "Mean throughput",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.RESOURCE_UTILIZATION
                )
            )

            val MeanProcessorUtilization = metricRepository.save(
                Metric(
                    name = "Mean processor utilization",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.RESOURCE_UTILIZATION
                )
            )

            val MeanMemoryUtilization = metricRepository.save(
                Metric(
                    name = "Mean memory utilization",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.RESOURCE_UTILIZATION
                )
            )

            val MeanIoDevicesUtilization = metricRepository.save(
                Metric(
                    name = "Mean IO devices utilization",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.RESOURCE_UTILIZATION
                )
            )

            val BandwidthUtilization = metricRepository.save(
                Metric(
                    name = "Bandwidth utilization",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.CAPACITY
                )
            )

            val TransactionProcessingCapacity = metricRepository.save(
                Metric(
                    name = "Transaction processing capacity",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.CAPACITY
                )
            )

            val UserAccessCapacity = metricRepository.save(
                Metric(
                    name = "User access capacity",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.CAPACITY
                )
            )

            val UserAccessIncreaseAdequacy = metricRepository.save(
                Metric(
                    name = "User access increase adequacy",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.PERFORMANCE_EFFICIENCY,
                    qualitySubCharacteristic = QualitySubCharacteristic.CAPACITY
                )
            )

            val PerformanceEfficiency = metricRepository.save(
                Metric(
                    name = "QA Performance Efficiency",
                    metrics = mutableSetOf(
                        MeanResponseTime,
                        ResponseTimeAdequacy,
                        MeanTurnaroundTime,
                        TurnaroundTimeAdequacy,
                        MeanThroughput,
                        MeanProcessorUtilization,
                        MeanMemoryUtilization,
                        MeanIoDevicesUtilization,
                        BandwidthUtilization,
                        TransactionProcessingCapacity,
                        UserAccessCapacity,
                        UserAccessIncreaseAdequacy
                    )
                )
            )


            // Compatibility
            val CoexistenceWithOtherProducts = metricRepository.save(
                Metric(
                    name = "Coexistence with other products",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.COMPATIBILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.COEXISTENCE
                )
            )

            val DataFormatsExchangeability = metricRepository.save(
                Metric(
                    name = "Data formats exchangeability",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.COMPATIBILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.INTEROPERABILITY
                )
            )

            val DataExchangeProtocolSufficiency = metricRepository.save(
                Metric(
                    name = "Data exchange protocol sufficiency",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.COMPATIBILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.INTEROPERABILITY
                )
            )

            val ExternalInterfaceAdequacy = metricRepository.save(
                Metric(
                    name = "External interface adequacy",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.COMPATIBILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.INTEROPERABILITY
                )
            )

            val compatibility = metricRepository.save(
                Metric(
                    name = "QA compatibility",
                    metrics = mutableSetOf(
                        CoexistenceWithOtherProducts,
                        DataFormatsExchangeability,
                        DataExchangeProtocolSufficiency,
                        ExternalInterfaceAdequacy
                    )
                )
            )

            val DescriptionCompleteness = metricRepository.save(
                Metric(
                    name = "Description completeness",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.APPROPRIATENESS_RECOGNISABILITY
                )
            )

            val DemonstrationCoverage = metricRepository.save(
                Metric(
                    name = "Demonstration coverage",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.APPROPRIATENESS_RECOGNISABILITY
                )
            )

            val EntryPointSelfDescriptiveness = metricRepository.save(
                Metric(
                    name = "Entry point self-descriptiveness",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.APPROPRIATENESS_RECOGNISABILITY
                )
            )

            val UserGuidanceCompleteness = metricRepository.save(
                Metric(
                    name = "User guidance completeness",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.LEARNABILITY
                )
            )

            val EntryFieldsDefaults = metricRepository.save(
                Metric(
                    name = "Entry fields defaults",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.LEARNABILITY
                )
            )

            val ErrorMessagesUnderstandability = metricRepository.save(
                Metric(
                    name = "Error messages understandability",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.LEARNABILITY
                )
            )

            val SelfExplanatoryUserInterface = metricRepository.save(
                Metric(
                    name = "Self-explanatory user interface",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val OperationalConsistency = metricRepository.save(
                Metric(
                    name = "Operational consistency",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val MessageClarity = metricRepository.save(
                Metric(
                    name = "Message clarity",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val FunctionalCustomizability = metricRepository.save(
                Metric(
                    name = "Functional customizability",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val UserInterfaceCustomizability = metricRepository.save(
                Metric(
                    name = "User interface customizability",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val MonitoringCapability = metricRepository.save(
                Metric(
                    name = "Monitoring capability",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val UndoCapability = metricRepository.save(
                Metric(
                    name = "Undo capability",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val UnderstandableCategorizationOfInformation = metricRepository.save(
                Metric(
                    name = "Understandable categorization of information",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val AppearanceConsistency = metricRepository.save(
                Metric(
                    name = "Appearance consistency",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.OPERABILITY
                )
            )

            val InputDeviceSupport = metricRepository.save(
                Metric(
                    name = "Input device support",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.USER_ERROR_PROTECTION
                )
            )

            val AvoidanceOfUserOperationError = metricRepository.save(
                Metric(
                    name = "Avoidance of user operation error",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.USER_ERROR_PROTECTION
                )
            )

            val UserEntryErrorCorrection = metricRepository.save(
                Metric(
                    name = "User entry error correction",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.USER_ERROR_PROTECTION
                )
            )

            val UserErrorRecoverability = metricRepository.save(
                Metric(
                    name = "User error recoverability",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.USER_INTERFACE_AESTHETICS
                )
            )

            val AppearanceAestheticsOfUserInterfaces = metricRepository.save(
                Metric(
                    name = "Appearance aesthetics of user interfaces",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.ACCESSIBILITY
                )
            )

            val AccessibilityForUsersWithDisabilities = metricRepository.save(
                Metric(
                    name = "Accessibility for users with disabilities",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.ACCESSIBILITY
                )
            )

            val SupportedLanguagesAdequacy = metricRepository.save(
                Metric(
                    name = "Supported languages adequacy",
                    calculates = mutableSetOf(
                        calculableRepository.save(
                            Calculable(
                                name = "value",
                                value = 1.0
                            )
                        )
                    ),
                    qualityCharacteristic = QualityCharacteristic.USABILITY,
                    qualitySubCharacteristic = QualitySubCharacteristic.ACCESSIBILITY
                )
            )

            val Usability = metricRepository.save(
                Metric(
                    name = "QA Usability",
                    metrics = mutableSetOf(
                        DescriptionCompleteness,
                        DemonstrationCoverage,
                        EntryPointSelfDescriptiveness,
                        UserGuidanceCompleteness,
                        EntryFieldsDefaults,
                        ErrorMessagesUnderstandability,
                        SelfExplanatoryUserInterface,
                        OperationalConsistency,
                        MessageClarity,
                        FunctionalCustomizability,
                        UserInterfaceCustomizability,
                        MonitoringCapability,
                        UndoCapability,
                        UnderstandableCategorizationOfInformation,
                        AppearanceConsistency,
                        InputDeviceSupport,
                        AvoidanceOfUserOperationError,
                        UserEntryErrorCorrection,
                        UserErrorRecoverability,
                        AppearanceAestheticsOfUserInterfaces,
                        AccessibilityForUsersWithDisabilities,
                        SupportedLanguagesAdequacy
                    )
                )
            )
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CalculatorApplication>(*args)
}
