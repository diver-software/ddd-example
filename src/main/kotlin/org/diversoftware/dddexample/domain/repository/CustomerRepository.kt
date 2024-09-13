package org.diversoftware.dddexample.domain.repository

import java.util.UUID
import org.diversoftware.dddexample.domain.model.Customer

interface CustomerRepository {
    fun findById(id: UUID): Customer?
    fun save(customer: Customer): Customer
    fun deleteById(id: UUID)
}
