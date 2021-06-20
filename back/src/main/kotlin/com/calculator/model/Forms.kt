package com.calculator.model

data class CalculableForm(
    val name: String,
    val left: Long?,
    val right: Long?,
    val operator: String?,
    val value: Double?
)

data class ProjectForm(
    val name: String,
    val description: String,
    val calculables: MutableList<Long>
)

data class CompanyForm(
    val name: String,
    val description: String,
    val projects: MutableList<Long>
)
