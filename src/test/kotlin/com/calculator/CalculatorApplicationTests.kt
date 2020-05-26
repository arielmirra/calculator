package com.calculator

import com.calculator.model.User
import com.calculator.model.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CalculatorApplicationTests @Autowired constructor(
        private val userRepository: UserRepository) {

    @BeforeAll
    fun setup() {
        println(">> Testing Setup")
        userRepository.deleteAll()
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun `When findByLogin then return User`() {
        val newUser = User( "123456789", "ariel", "arimirra@hotmail.com")
        userRepository.saveAndFlush(newUser)
        val user: User? = userRepository.findByName(newUser.name)
        Assertions.assertThat(user!!.name).isEqualTo("ariel")
        Assertions.assertThat(user).isEqualTo(newUser)
    }

    @AfterAll
    fun teardown() {
        println(">> Testing Tear down")
    }

}

