package com.calculator.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "users")
data class User(
        @Id @GeneratedValue var id: String = "",
        var name: String = "",
        var email: String = ""
)

@Entity(name = "catalogs")
data class Catalog(
        @Id @GeneratedValue var id: String = "",
        var name: String = "",
        var description: String = "",
        @OneToMany var catalogElements: MutableList<Element>
)

@Entity(name = "elements")
data class Element(
        @Id @GeneratedValue var id: String = "",
        var name: String = "",
        var description: String = "",
        var type: ElementType,
        @OneToMany var metrics: MutableList<Metric>,
        @OneToMany var elements: MutableList<Element>
)

enum class ElementType {
    QualityCharacteristic,
    QualitySubCharacteristic
    // more more more
}

@Entity(name = "metrics")
data class Metric(
        @Id @GeneratedValue var id: String = "",
        var name: String = "",
        var standard: String = "",
        @OneToMany var calculus: MutableList<Calculus>
)

@Entity(name = "calculus")
abstract class Calculus {
    @Id @GeneratedValue var id: String = ""
}
