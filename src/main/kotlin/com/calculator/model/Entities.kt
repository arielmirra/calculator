package com.calculator.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity(name = "users")
data class User(
        @Id @GeneratedValue var id: Int = -1,
        var name: String = "",
        var email: String = ""
)

@Entity(name = "catalogs")
data class Catalog(
        @Id @GeneratedValue var id: Int = -1,
        var name: String = "",
        var description: String = "",
        @OneToMany(fetch = FetchType.EAGER) var catalogElements: MutableList<Element>
)

@Entity(name = "elements")
data class Element(
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "uuid")
        var uuid: String = "",

        var name: String = "",

        var description: String = "",

        var type: ElementType,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "parent", referencedColumnName = "uuid")
        var parent: Element?,

        @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade = [CascadeType.ALL])
        var elements: MutableList<Element>?
//        @OneToMany(fetch = FetchType.EAGER) var metrics: MutableList<Metric>,
)

enum class ElementType {
    QualityCharacteristic,
    QualitySubCharacteristic,
    Metric,
    Goal,
    Question,
    // should Metric inherit from Element?
}

@Entity(name = "metrics")
data class Metric(
        @Id @GeneratedValue var id: Int = -1,
        var name: String = "",
        var standard: String = "",
        @OneToMany(fetch = FetchType.EAGER) var calculus: MutableList<Calculus>
)

@Entity(name = "calculus")
data class Calculus(@Id @GeneratedValue var id: Int = -1) {
}
