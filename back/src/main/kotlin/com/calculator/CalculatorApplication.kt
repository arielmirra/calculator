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

    //@Bean
    fun googleSheetSetup(
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

            // ISO/IEC 25023 variables and composite metrics
            // --------------------------------------- FIRST LEVEL ---------------------------------------

            val functionalCoverage = calculableRepository.save(Calculable(
                name = "FunctionalCoverage",
                calculableType = CalculableType.VARIABLE,
            ))

            val functionalCorrectness = calculableRepository.save(Calculable(
                name = "FunctionalCorrectness",
                calculableType = CalculableType.VARIABLE,
            ))

            val functionalAppropriatenessOfUsageObjective = calculableRepository.save(Calculable(
                name = "FunctionalAppropriatenessOfUsageObjective",
                calculableType = CalculableType.VARIABLE,
            ))

            val functionalAppropriatenessOfTheSystem = calculableRepository.save(Calculable(
                name = "FunctionalAppropriatenessOfTheSystem",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanResponseTime = calculableRepository.save(Calculable(
                name = "MeanResponseTime",
                calculableType = CalculableType.VARIABLE,
            ))

            val ResponseTimeAdequacy = calculableRepository.save(Calculable(
                name = "ResponseTimeAdequacy",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanTurnaroundTime = calculableRepository.save(Calculable(
                name = "MeanTurnaroundTime",
                calculableType = CalculableType.VARIABLE,
            ))

            val TurnaroundTimeAdequacy = calculableRepository.save(Calculable(
                name = "TurnaroundTimeAdequacy",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanThroughput = calculableRepository.save(Calculable(
                name = "MeanThroughput",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanProcessorUtilization = calculableRepository.save(Calculable(
                name = "MeanProcessorUtilization",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanMemoryUtilization = calculableRepository.save(Calculable(
                name = "MeanMemoryUtilization",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanIODevicesUtilization = calculableRepository.save(Calculable(
                name = "MeanIODevicesUtilization",
                calculableType = CalculableType.VARIABLE,
            ))

            val BandwidthUtilization = calculableRepository.save(Calculable(
                name = "BandwidthUtilization",
                calculableType = CalculableType.VARIABLE,
            ))

            val TransactionProcessingCapacity = calculableRepository.save(Calculable(
                name = "TransactionProcessingCapacity",
                calculableType = CalculableType.VARIABLE,
            ))

            val UserAccessCapacity = calculableRepository.save(Calculable(
                name = "UserAccessCapacity",
                calculableType = CalculableType.VARIABLE,
            ))

            val UserAccessIncreaseAdequacy = calculableRepository.save(Calculable(
                name = "UserAccessIncreaseAdequacy",
                calculableType = CalculableType.VARIABLE,
            ))

            val CoExistenceWithOtherProducts = calculableRepository.save(Calculable(
                name = "CoExistenceWithOtherProducts",
                calculableType = CalculableType.VARIABLE,
            ))

            val DataFormatsExchangeability = calculableRepository.save(Calculable(
                name = "DataFormatsExchangeability",
                calculableType = CalculableType.VARIABLE,
            ))

            val DataExchangeProtocolSufficiency = calculableRepository.save(Calculable(
                name = "DataExchangeProtocolSufficiency",
                calculableType = CalculableType.VARIABLE,
            ))

            val ExternalInterfaceAdequacy = calculableRepository.save(Calculable(
                name = "ExternalInterfaceAdequacy",
                calculableType = CalculableType.VARIABLE,
            ))

            val DescriptionCompleteness = calculableRepository.save(Calculable(
                name = "DescriptionCompleteness",
                calculableType = CalculableType.VARIABLE,
            ))

            val DemonstrationCoverage = calculableRepository.save(Calculable(
                name = "DemonstrationCoverage",
                calculableType = CalculableType.VARIABLE,
            ))

            val EntryPointSelfDescriptiveness = calculableRepository.save(Calculable(
                name = "EntryPointSelfDescriptiveness",
                calculableType = CalculableType.VARIABLE,
            ))

            val UserGuidanceCompleteness = calculableRepository.save(Calculable(
                name = "UserGuidanceCompleteness",
                calculableType = CalculableType.VARIABLE,
            ))

            val EntryFieldsDefaults = calculableRepository.save(Calculable(
                name = "EntryFieldsDefaults",
                calculableType = CalculableType.VARIABLE,
            ))

            val ErrorMessagesUnderstandability = calculableRepository.save(Calculable(
                name = "ErrorMessagesUnderstandability",
                calculableType = CalculableType.VARIABLE,
            ))

            val SelfExplanatoryUserInterface = calculableRepository.save(Calculable(
                name = "SelfExplanatoryUserInterface",
                calculableType = CalculableType.VARIABLE,
            ))

            val OperationalConsistency = calculableRepository.save(Calculable(
                name = "OperationalConsistency",
                calculableType = CalculableType.VARIABLE,
            ))

            val MessageClarity = calculableRepository.save(Calculable(
                name = "MessageClarity",
                calculableType = CalculableType.VARIABLE,
            ))

            val FunctionalCustomizability = calculableRepository.save(Calculable(
                name = "FunctionalCustomizability",
                calculableType = CalculableType.VARIABLE,
            ))

            val UserInterfaceCustomizability = calculableRepository.save(Calculable(
                name = "UserInterfaceCustomizability",
                calculableType = CalculableType.VARIABLE,
            ))

            val MonitoringCapability = calculableRepository.save(Calculable(
                name = "MonitoringCapability",
                calculableType = CalculableType.VARIABLE,
            ))

            val UndoCapability = calculableRepository.save(Calculable(
                name = "UndoCapability",
                calculableType = CalculableType.VARIABLE,
            ))

            val UnderstandableCategorizationOfInformation = calculableRepository.save(Calculable(
                name = "UnderstandableCategorizationOfInformation",
                calculableType = CalculableType.VARIABLE,
            ))

            val AppearanceConsistency = calculableRepository.save(Calculable(
                name = "AppearanceConsistency",
                calculableType = CalculableType.VARIABLE,
            ))

            val InputDeviceSupport = calculableRepository.save(Calculable(
                name = "InputDeviceSupport",
                calculableType = CalculableType.VARIABLE,
            ))

            val AvoidanceOfUserOperationError = calculableRepository.save(Calculable(
                name = "AvoidanceOfUserOperationError",
                calculableType = CalculableType.VARIABLE,
            ))

            val UserEntryErrorCorrection = calculableRepository.save(Calculable(
                name = "UserEntryErrorCorrection",
                calculableType = CalculableType.VARIABLE,
            ))

            val UserErrorRecoverability = calculableRepository.save(Calculable(
                name = "UserErrorRecoverability",
                calculableType = CalculableType.VARIABLE,
            ))

            val AppearanceAestheticsOfUserInterfaces = calculableRepository.save(Calculable(
                name = "AppearanceAestheticsOfUserInterfaces",
                calculableType = CalculableType.VARIABLE,
            ))

            val AccessibilityForUsersWithDisabilities = calculableRepository.save(Calculable(
                name = "AccessibilityForUsersWithDisabilities",
                calculableType = CalculableType.VARIABLE,
            ))

            val SupportedLanguagesAdequacy = calculableRepository.save(Calculable(
                name = "SupportedLanguagesAdequacy",
                calculableType = CalculableType.VARIABLE,
            ))

            val FaultCorrection = calculableRepository.save(Calculable(
                name = "FaultCorrection",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanTimeBetweenFailure = calculableRepository.save(Calculable(
                name = "MeanTimeBetweenFailure",
                calculableType = CalculableType.VARIABLE,
            ))

            val FailureRate = calculableRepository.save(Calculable(
                name = "FailureRate",
                calculableType = CalculableType.VARIABLE,
            ))

            val TestCoverage = calculableRepository.save(Calculable(
                name = "TestCoverage",
                calculableType = CalculableType.VARIABLE,
            ))

            val SystemAvailability = calculableRepository.save(Calculable(
                name = "SystemAvailability",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanDownTime = calculableRepository.save(Calculable(
                name = "MeanDownTime",
                calculableType = CalculableType.VARIABLE,
            ))

            val FailureAvoidance = calculableRepository.save(Calculable(
                name = "FailureAvoidance",
                calculableType = CalculableType.VARIABLE,
            ))

            val RedundancyOfComponents = calculableRepository.save(Calculable(
                name = "RedundancyOfComponents",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanFaultNotificationTime = calculableRepository.save(Calculable(
                name = "MeanFaultNotificationTime",
                calculableType = CalculableType.VARIABLE,
            ))

            val MeanRecoveryTime = calculableRepository.save(Calculable(
                name = "MeanRecoveryTime",
                calculableType = CalculableType.VARIABLE,
            ))

            val BackupDataCompleteness = calculableRepository.save(Calculable(
                name = "BackupDataCompleteness",
                calculableType = CalculableType.VARIABLE,
            ))

            val AccessControllability = calculableRepository.save(Calculable(
                name = "AccessControllability",
                calculableType = CalculableType.VARIABLE,
            ))

            val DataEncryptionCorrectness = calculableRepository.save(Calculable(
                name = "DataEncryptionCorrectness",
                calculableType = CalculableType.VARIABLE,
            ))

            val StrengthOfCryptographicAlgorithms = calculableRepository.save(Calculable(
                name = "StrengthOfCryptographicAlgorithms",
                calculableType = CalculableType.VARIABLE,
            ))

            val DataIntegrity = calculableRepository.save(Calculable(
                name = "DataIntegrity",
                calculableType = CalculableType.VARIABLE,
            ))

            val InternalDataCorruptionPrevention = calculableRepository.save(Calculable(
                name = "InternalDataCorruptionPrevention",
                calculableType = CalculableType.VARIABLE,
            ))

            val BufferOverflowPrevention = calculableRepository.save(Calculable(
                name = "BufferOverflowPrevention",
                calculableType = CalculableType.VARIABLE,
            ))

            val DigitalSignatureUsage = calculableRepository.save(Calculable(
                name = "DigitalSignatureUsage",
                calculableType = CalculableType.VARIABLE,
            ))

            val UserAuditTrailCompleteness = calculableRepository.save(Calculable(
                name = "UserAuditTrailCompleteness",
                calculableType = CalculableType.VARIABLE,
            ))

            val SystemLogRetention = calculableRepository.save(Calculable(
                name = "SystemLogRetention",
                calculableType = CalculableType.VARIABLE,
            ))

            val AuthenticationMechanismSufficiency = calculableRepository.save(Calculable(
                name = "AuthenticationMechanismSufficiency",
                calculableType = CalculableType.VARIABLE,
            ))

            val AuthenticationRulesConformity = calculableRepository.save(Calculable(
                name = "AuthenticationRulesConformity",
                calculableType = CalculableType.VARIABLE,
            ))

            val CouplingOfComponents = calculableRepository.save(Calculable(
                name = "CouplingOfComponents",
                calculableType = CalculableType.VARIABLE,
            ))

            val CyclomaticComplexityAdequacy = calculableRepository.save(Calculable(
                name = "CyclomaticComplexityAdequacy",
                calculableType = CalculableType.VARIABLE,
            ))

            val ReusabilityAssets = calculableRepository.save(Calculable(
                name = "ReusabilityAssets",
                calculableType = CalculableType.VARIABLE,
            ))

            val CodingRulesConformity = calculableRepository.save(Calculable(
                name = "CodingRulesConformity",
                calculableType = CalculableType.VARIABLE,
            ))

            val SystemLogCompleteness = calculableRepository.save(Calculable(
                name = "SystemLogCompleteness",
                calculableType = CalculableType.VARIABLE,
            ))

            val DiagnosisFunctionEffectiveness = calculableRepository.save(Calculable(
                name = "DiagnosisFunctionEffectiveness",
                calculableType = CalculableType.VARIABLE,
            ))

            val DiagnosisFunctionSufficiency = calculableRepository.save(Calculable(
                name = "DiagnosisFunctionSufficiency",
                calculableType = CalculableType.VARIABLE,
            ))

            val ModificationEfficiency = calculableRepository.save(Calculable(
                name = "ModificationEfficiency",
                calculableType = CalculableType.VARIABLE,
            ))

            val ModificationCorrectness = calculableRepository.save(Calculable(
                name = "ModificationCorrectness",
                calculableType = CalculableType.VARIABLE,
            ))

            val ModificationCapability = calculableRepository.save(Calculable(
                name = "ModificationCapability",
                calculableType = CalculableType.VARIABLE,
            ))

            val TestFunctionCompleteness = calculableRepository.save(Calculable(
                name = "TestFunctionCompleteness",
                calculableType = CalculableType.VARIABLE,
            ))

            val AutonomousTestability = calculableRepository.save(Calculable(
                name = "AutonomousTestability",
                calculableType = CalculableType.VARIABLE,
            ))

            val TestRestartability = calculableRepository.save(Calculable(
                name = "TestRestartability",
                calculableType = CalculableType.VARIABLE,
            ))

            val HardwareEnvironmentalAdaptability = calculableRepository.save(Calculable(
                name = "HardwareEnvironmentalAdaptability",
                calculableType = CalculableType.VARIABLE,
            ))

            val SystemSoftwareEnvironmentalAdaptability = calculableRepository.save(Calculable(
                name = "SystemSoftwareEnvironmentalAdaptability",
                calculableType = CalculableType.VARIABLE,
            ))

            val OperationalEnvironmentAdaptability = calculableRepository.save(Calculable(
                name = "OperationalEnvironmentAdaptability",
                calculableType = CalculableType.VARIABLE,
            ))

            val InstallationTimeEfficiency = calculableRepository.save(Calculable(
                name = "InstallationTimeEfficiency",
                calculableType = CalculableType.VARIABLE,
            ))

            val EaseOfInstallation = calculableRepository.save(Calculable(
                name = "EaseOfInstallation",
                calculableType = CalculableType.VARIABLE,
            ))

            val UsageSimilarity = calculableRepository.save(Calculable(
                name = "UsageSimilarity",
                calculableType = CalculableType.VARIABLE,
            ))

            val ProductQualityEquivalence = calculableRepository.save(Calculable(
                name = "ProductQualityEquivalence",
                calculableType = CalculableType.VARIABLE,
            ))

            val FunctionalInclusiveness = calculableRepository.save(Calculable(
                name = "FunctionalInclusiveness",
                calculableType = CalculableType.VARIABLE,
            ))

            val DataReusabilityImportCapability = calculableRepository.save(Calculable(
                name = "DataReusabilityImportCapability",
                calculableType = CalculableType.VARIABLE,
            ))

            // --------------------------------------- SECOND LEVEL ---------------------------------------

            val FunctionalAppropriateness = calculableRepository.save(Calculable(
                name = "Functional appropriateness",
                description = "refers to what portion of the functions required by the user provides the appropriate outcome to achieve a specific usage objective",
                left = functionalAppropriatenessOfUsageObjective,
                right = functionalAppropriatenessOfTheSystem,
                operator = Operator.PLUS,
                calculableType = CalculableType.METRIC
            ))

            val TimeBehavior = calculableRepository.save(Calculable(
                name = "Time behavior",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(MeanResponseTime, ResponseTimeAdequacy, MeanTurnaroundTime, TurnaroundTimeAdequacy, MeanThroughput)
            ))

            val ResourceUtilization = calculableRepository.save(Calculable(
                name = "Resource utilization",
                description = "indicate the consumption of CPU time, memory, and bandwidth for given performance measures",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(MeanProcessorUtilization, MeanMemoryUtilization, MeanIODevicesUtilization, BandwidthUtilization)
            ))

            val capacity = calculableRepository.save(Calculable(
                name = "Capacity",
                description = "the ability of the system to accommodate simultaneous user access",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(UserAccessIncreaseAdequacy, UserAccessCapacity, TransactionProcessingCapacity)
            ))

            val interoperability = calculableRepository.save(Calculable(
                name = "interoperability",
                description = "the ability of the system to accommodate simultaneous user access",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(DataFormatsExchangeability, DataExchangeProtocolSufficiency, ExternalInterfaceAdequacy)
            ))

            val appropriatenessRecognisability = calculableRepository.save(Calculable(
                name = "Appropriateness recognisability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(DescriptionCompleteness, DemonstrationCoverage, EntryPointSelfDescriptiveness)
            ))

            val learnability = calculableRepository.save(Calculable(
                name = "learnability",
                description = "Understanding of why an error has happened",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(UserGuidanceCompleteness, EntryFieldsDefaults, ErrorMessagesUnderstandability, SelfExplanatoryUserInterface)
            ))


            val operability = calculableRepository.save(Calculable(
                name = "operability",
                description = "The correctness of instructions from the product to the user",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(OperationalConsistency, MessageClarity, FunctionalCustomizability, UserInterfaceCustomizability, MonitoringCapability, UndoCapability, UnderstandableCategorizationOfInformation, AppearanceConsistency, InputDeviceSupport)
            ))

            val userErrorProtection = calculableRepository.save(Calculable(
                name = "User error protection",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(AvoidanceOfUserOperationError, UserEntryErrorCorrection, UserErrorRecoverability)
            ))

            val accessibility = calculableRepository.save(Calculable(
                name = "accessibility",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(AccessibilityForUsersWithDisabilities, SupportedLanguagesAdequacy)
            ))

            val maturity = calculableRepository.save(Calculable(
                name = "maturity",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(FaultCorrection, MeanTimeBetweenFailure, FailureRate, TestCoverage)
            ))

            val availability = calculableRepository.save(Calculable(
                name = "availability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(SystemAvailability, MeanDownTime)
            ))

            val faultTolerance = calculableRepository.save(Calculable(
                name = "Fault Tolerance",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(FailureAvoidance, RedundancyOfComponents, MeanFaultNotificationTime)
            ))

            val recoverability = calculableRepository.save(Calculable(
                name = "recoverability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(MeanRecoveryTime, BackupDataCompleteness)
            ))

            val confidentiality = calculableRepository.save(Calculable(
                name = "confidentiality",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(AccessControllability, DataEncryptionCorrectness, StrengthOfCryptographicAlgorithms)
            ))

            val integrity = calculableRepository.save(Calculable(
                name = "integrity",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(DataIntegrity, InternalDataCorruptionPrevention, BufferOverflowPrevention)
            ))

            val authenticity = calculableRepository.save(Calculable(
                name = "authenticity",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(AuthenticationMechanismSufficiency, AuthenticationRulesConformity)
            ))

            val accountability = calculableRepository.save(Calculable(
                name = "accountability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(UserAuditTrailCompleteness, SystemLogRetention)
            ))

            val modularity = calculableRepository.save(Calculable(
                name = "modularity",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(CouplingOfComponents, CyclomaticComplexityAdequacy)
            ))

            val reusability = calculableRepository.save(Calculable(
                name = "reusability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(ReusabilityAssets, CodingRulesConformity)
            ))

            val analyzability = calculableRepository.save(Calculable(
                name = "analyzability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(SystemLogCompleteness, DiagnosisFunctionEffectiveness, DiagnosisFunctionSufficiency)
            ))

            val modifiability = calculableRepository.save(Calculable(
                name = "modifiability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(ModificationEfficiency, ModificationCorrectness, ModificationCapability)
            ))

            val testability = calculableRepository.save(Calculable(
                name = "testability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(TestFunctionCompleteness, AutonomousTestability, TestRestartability)
            ))

            val adaptability = calculableRepository.save(Calculable(
                name = "adaptability",
                description = "ratios of software functions that successfully operate on new conditions",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(HardwareEnvironmentalAdaptability, SystemSoftwareEnvironmentalAdaptability, OperationalEnvironmentAdaptability)
            ))

            val installability = calculableRepository.save(Calculable(
                name = "installability",
                description = "ease of installation",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(InstallationTimeEfficiency, EaseOfInstallation)
            ))

            val replaceability = calculableRepository.save(Calculable(
                name = "replaceability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(UsageSimilarity, ProductQualityEquivalence, FunctionalInclusiveness, DataReusabilityImportCapability)
            ))


            // --------------------------------------- THIRD LEVEL ---------------------------------------


            val functionalSuitability = calculableRepository.save(Calculable(
                name = "Functional suitability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(functionalCoverage, functionalCorrectness, FunctionalAppropriateness)
            ))

            val performanceEfficiency = calculableRepository.save(Calculable(
                name = "Performance Efficiency",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(TimeBehavior, ResourceUtilization, capacity)
            ))

            val compatibility = calculableRepository.save(Calculable(
                name = "compatibility",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(CoExistenceWithOtherProducts, interoperability)
            ))

            val usability = calculableRepository.save(Calculable(
                name = "usability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(appropriatenessRecognisability, learnability, operability, userErrorProtection, AppearanceAestheticsOfUserInterfaces, accessibility)
            ))

            val reliability = calculableRepository.save(Calculable(
                name = "reliability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(maturity, availability, faultTolerance, recoverability)
            ))

            val security = calculableRepository.save(Calculable(
                name = "security",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(confidentiality, integrity, DigitalSignatureUsage, accountability, authenticity)
            ))

            val maintainability = calculableRepository.save(Calculable(
                name = "maintainability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(modularity, reusability, analyzability, modifiability, testability)
            ))

            val portability = calculableRepository.save(Calculable(
                name = "portability",
                description = "N/A",
                operator = Operator.AVERAGE,
                calculableType = CalculableType.METRIC,
                children = mutableSetOf(adaptability, installability, replaceability)
            ))
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
