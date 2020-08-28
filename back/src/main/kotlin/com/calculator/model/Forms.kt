package com.calculator.model

data class CalculableForm(
        val name: String,
        val left: String?,
        val right: String?,
        val operator: String?,
        val value: String?
) // todo: handle saving & relationships
