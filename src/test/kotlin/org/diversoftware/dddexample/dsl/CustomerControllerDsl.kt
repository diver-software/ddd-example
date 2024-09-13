package org.diversoftware.dddexample.dsl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import org.diversoftware.dddexample.api.controller.CustomerController
import org.diversoftware.dddexample.api.dto.CustomerDto
import org.diversoftware.dddexample.api.dto.toDto
import org.diversoftware.dddexample.domain.model.Customer
import org.diversoftware.dddexample.domain.service.CustomerService
import org.instancio.Instancio
import org.instancio.Select
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CustomerControllerDsl {

    private val customerService: CustomerService = mockk(relaxed = true)
    private val customerController = CustomerController(customerService)

    private lateinit var customerId: UUID
    private lateinit var response: ResponseEntity<*>
    private lateinit var customerDto: CustomerDto
    private lateinit var customer: Customer

    // Setup
    fun `a customer exists in the system`() = apply {
        customerId = UUID.randomUUID()
        customer = Instancio.of(Customer::class.java) // TODO: extract this?
            .set(Select.field("id"), customerId)
            .create()
        customerDto = customer.toDto()
        every { customerService.get(customerId) } returns customer
    }

    fun `no customer exists in the system`() = apply {
        customerId = UUID.randomUUID()
        every { customerService.get(customerId) } returns null
    }

    fun `a valid customer creation request is provided`() = apply {
        customer = Instancio.of(Customer::class.java).create()
        customerDto = customer.toDto()
        every { customerService.create(customer) } returns customer
    }

    // Actions
    fun `the customer is requested by ID`() = apply {
        response = customerController.getCustomer(customerId)
    }

    fun `a request is made to create a new customer`() = apply {
        response = customerController.createCustomer(customerDto)
    }

    fun `a request is made to delete a customer`() = apply {
        response = customerController.deleteCustomer(customerId)
    }

    // Verifications
    fun `the response should be OK with customer details`() = apply {
        assert(response.statusCode == HttpStatus.OK)
        assert(response.body == customerDto)
    }

    fun `the response should be not found`() = apply {
        assert(response.statusCode == HttpStatus.NOT_FOUND)
    }

    fun `the response should indicate no content`() = apply {
        assert(response.statusCode == HttpStatus.NO_CONTENT)
    }

    fun `the service should have been called to get the customer`() = apply {
        verify { customerService.get(customerId) }
    }

    fun `the service should have been called to create the customer`() = apply {
        verify { customerService.create(customer) }
    }

    fun `the service should have been called to delete the customer`() = apply {
        verify { customerService.delete(customerId) }
    }
}
