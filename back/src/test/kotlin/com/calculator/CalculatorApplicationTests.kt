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
        @Autowired private val userRepository: UserRepository,
        @Autowired private val elementRepository: ElementRepository,
        @Autowired private val catalogRepository: CatalogRepository
) {

    @BeforeAll
    fun setup() {
        println(">> Testing Setup")
        userRepository.deleteAll()
        catalogRepository.deleteAll()
        elementRepository.deleteAll()
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun `When findByLogin then return User`() {
        val newUser = User(name = "ariel", email = "arimirra@hotmail.com")
        userRepository.saveAndFlush(newUser)
        val user = userRepository.findByName(newUser.name)!!
        assertThat(user.name).isEqualTo("ariel")
    }

    @Test
    fun `Composite Pattern POC`() {
        val elementName = "Quality Characteristic"
        val catalogName = "example catalog"

        val child = Element(
                name = elementName,
                description = "description",
                type = ElementType.QualityCharacteristic
        )

        val parent = Element(
                name = elementName,
                description = "description",
                type = ElementType.QualityCharacteristic,
                parent = null,
                elements = mutableListOf(child)
        )

        child.parent = parent
        elementRepository.saveAndFlush(parent)

        val e = elementRepository.findByName(elementName)!!
        println(e)
        assertThat(e.name).isEqualTo(elementName)

        val catalog = Catalog(
                0,
                catalogName,
                "description",
                mutableListOf(e)
        )

        catalogRepository.saveAndFlush(catalog)
        val c = catalogRepository.findByName(catalogName)!!
        assertThat(c.catalogElements.size).isEqualTo(1)
    }

    @AfterAll
    fun teardown() {
        println(">> Testing Tear down")
    }

}

