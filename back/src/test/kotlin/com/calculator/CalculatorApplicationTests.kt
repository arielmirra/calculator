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
//        @Autowired private val elementRepository: ElementRepository,
) {

    @BeforeAll
    fun setup() {
        println(">> Testing Setup")
    }

    @Test
    fun contextLoads() {
    }

    @AfterAll
    fun teardown() {
        println(">> Testing Tear down")
    }

}

