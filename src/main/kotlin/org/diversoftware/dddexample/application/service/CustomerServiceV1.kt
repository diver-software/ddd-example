package org.diversoftware.dddexample.application.service

import java.util.*
import org.diversoftware.dddexample.domain.model.Customer
import org.diversoftware.dddexample.domain.repository.CustomerRepository
import org.diversoftware.dddexample.domain.service.CustomerService
import org.springframework.stereotype.Service

@Service
class CustomerServiceV1(
    private val customerRepository: CustomerRepository
) : CustomerService {

    override fun get(id: UUID): Customer? {
        return customerRepository.findById(id)
    }

    override fun create(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    override fun delete(id: UUID) {
        customerRepository.deleteById(id)
    }
}
