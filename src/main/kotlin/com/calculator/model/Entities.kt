package com.calculator.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "users")
data class User(
        @Id var id: String = "",
        var name: String = "",
        var email: String = ""
)
