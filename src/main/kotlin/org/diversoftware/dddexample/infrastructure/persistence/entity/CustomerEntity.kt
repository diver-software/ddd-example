package org.diversoftware.dddexample.infrastructure.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*
import org.diversoftware.dddexample.domain.model.Customer

@Entity
data class CustomerEntity(
    @Id
    val id: UUID,
    val name: String,
    val email: String
)

fun CustomerEntity.toDomain() = Customer(
    id = this.id,
    name = this.name,
    email = this.email,
)

fun Customer.toEntity() = CustomerEntity(
    id = this.id,
    name = this.name,
    email = this.email,
)
