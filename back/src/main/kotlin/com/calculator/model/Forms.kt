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
    val metrics: MutableList<Long>,
    val calculates: MutableList<Long>
)

data class ProjectForm(
    val name: String,
    val description: String,
    val metrics: MutableList<Long>
)

data class CompanyForm(
    val name: String,
    val description: String,
    val projects: MutableList<Long>
)
