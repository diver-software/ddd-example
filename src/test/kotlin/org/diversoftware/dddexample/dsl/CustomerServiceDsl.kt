package org.diversoftware.dddexample.dsl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import org.diversoftware.dddexample.application.service.CustomerServiceV1
import org.diversoftware.dddexample.domain.model.Customer
import org.diversoftware.dddexample.domain.repository.CustomerRepository
import org.instancio.Instancio
import org.instancio.Select

class CustomerServiceDsl {

    private val customerRepository: CustomerRepository = mockk(relaxed = true)
    private val customerService = CustomerServiceV1(customerRepository)

    private lateinit var customerId: UUID
    private lateinit var customer: Customer

    // Setup for scenarios
    fun `a customer exists in the repository`() = apply {
        customerId = UUID.randomUUID()
        customer = Instancio.of(Customer::class.java)
            .set(Select.field("id"), customerId)
            .create()
        every { customerRepository.findById(customerId) } returns customer
    }

    fun `no customer exists in the repository`() = apply {
        customerId = UUID.randomUUID()
        every { customerRepository.findById(customerId) } returns null
    }

    fun `a customer is ready to be saved`() = apply {
        customer = Instancio.of(Customer::class.java).create()
        every { customerRepository.save(customer) } returns customer
    }

    // Actions
    fun `the service is asked to get a customer`() = apply {
        customerService.get(customerId)
    }

    fun `the service is asked to create a customer`() = apply {
        customerService.create(customer)
    }

    fun `the service is asked to delete a customer`() = apply {
        customerService.delete(customerId)
    }

    // Verifications
    fun `the repository should be called to find the customer`() = apply {
        verify { customerRepository.findById(customerId) }
    }

    fun `the repository should be called to save the customer`() = apply {
        verify { customerRepository.save(customer) }
    }

    fun `the repository should be called to delete the customer`() = apply {
        verify { customerRepository.deleteById(customerId) }
    }
}