package org.diversoftware.dddexample.api.dto

import java.util.*
import org.diversoftware.dddexample.domain.model.Customer

data class CustomerDto(
    val id: UUID?,
    val name: String,
    val email: String
)

fun CustomerDto.toDomain() = Customer(
    id = this.id ?: UUID.randomUUID(),
    name = this.name,
    email = this.email,
)

fun Customer.toDto() = CustomerDto(
    id = this.id,
    name = this.name,
    email = this.email,
)
