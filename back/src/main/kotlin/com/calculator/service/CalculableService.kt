package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CalculableService(
        @Autowired private val calculableRepository: CalculableRepository
) {

    fun findByName(name: String): Calculable? = calculableRepository.findByName(name)
    fun findById(id: Long): Optional<Calculable> = calculableRepository.findById(id)
    fun save(calculable: Calculable) = calculableRepository.save(calculable)
    fun create(calculable: CalculableForm): Calculable? {
        TODO("Not yet implemented")
    }

}
