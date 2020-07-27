package com.calculator.model

interface Calculable {
    fun calculate(): Double
}

interface Measurable {
    fun measure(): Measurement // should be Unit and just save the Measurement?
}
