package com.calculator.model

data class CalculableForm(
        val name: String,
        val left: Long?,
        val right: Long?,
        val operator: String?,
        val value: Double?
)
