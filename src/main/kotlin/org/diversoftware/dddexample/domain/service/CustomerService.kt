package org.diversoftware.dddexample.domain.service

import java.util.*
import org.diversoftware.dddexample.domain.model.Customer

interface CustomerService {
    fun get(id: UUID): Customer?
    fun create(customer: Customer): Customer
    fun delete(id: UUID)
}