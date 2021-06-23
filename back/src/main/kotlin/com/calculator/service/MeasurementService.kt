package com.calculator.service

import com.calculator.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MeasurementService(
    @Autowired private val measurementRepository: MeasurementRepository,
) {
    fun getAll(): List<Measurement> = measurementRepository.findAll()
    fun findById(id: Long): Measurement? = measurementRepository.findByIdOrNull(id)
    fun save(measurement: Measurement) = measurementRepository.save(measurement)
    fun deleteById(id: Long) = measurementRepository.deleteById(id)

    fun findAllFromMetricId(id: Long): List<Measurement> =
        measurementRepository.findAllByFrom_FromId(id)


    fun delete(id: Long): Boolean = findById(id)?.let {
        deleteById(id)
        true
    } ?: false
}
