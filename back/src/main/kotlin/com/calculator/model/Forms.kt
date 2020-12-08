package com.calculator.model

data class CalculableForm(
        val name: String,
        val left: Long?,
        val right: Long?,
        val operator: String?,
        val value: Double?
)

data class MetricForm(
        val name: String,
        val description: String,
        val metrics: MutableList<Any>,
        val calculates: MutableList<Any>
)
