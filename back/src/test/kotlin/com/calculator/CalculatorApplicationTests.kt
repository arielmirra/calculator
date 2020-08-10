package com.calculator

import com.calculator.model.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CalculatorApplicationTests @Autowired constructor(
        @Autowired private val metricSetRepository: MetricSetRepository,
        @Autowired private val attributeRepository: AttributeRepository,
        @Autowired private val metricRepository: MetricRepository,
        @Autowired private val calculusRepository: CalculusRepository,
        @Autowired private val valueRepository: ValueRepository,
        @Autowired private val measurementRepository: MeasurementRepository
) {

    @BeforeAll
    fun setup() {
        println(">> Testing Setup")
        metricSetRepository.deleteAll()
        attributeRepository.deleteAll()
        metricRepository.deleteAll()
        calculusRepository.deleteAll()
        valueRepository.deleteAll()
        measurementRepository.deleteAll()
    }

    @Test
    fun testRepositories() {
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

        val calc = calculusRepository.findByName("5 * 2")!!
        println(fiveTimesTwo)
        println(calc)
        println("5 * 2 = ${calc.calculate()}")


        // metric
        val metric = metricRepository.save(Metric(
                name = "Metric 1",
                description = "Measures a calculus",
                calculates = mutableSetOf(fiveTimesTwo)
        ))

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

        val parent = Attribute(
                name = "parent",
                description = "this node has children"
        )
        attributeRepository.save(parent)

        set1.hasAttribute(parent)
        set2.hasAttribute(parent)
        set1.measures(set2)
        set2.measures(set3)
        metricSetRepository.saveAll(setOf(set1, set2))

        println("MetricSet1 Measure Result: ${set1.measure()}")
    }

    @AfterAll
    fun teardown() {
        println(">> Testing Tear down")
    }

}

