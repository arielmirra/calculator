package com.calculator.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByName(name: String): User?
}

@Repository
interface CatalogRepository : JpaRepository<Catalog, String> {
    fun findByName(name: String): Catalog?
}

@Repository
interface ElementRepository : JpaRepository<Element, String> {
    fun findByName(name: String): Element?
}
