package org.diversoftware.dddexample.api.controller

import org.diversoftware.dddexample.dsl.CustomerControllerDsl
import org.junit.jupiter.api.Test

class CustomerControllerTest {

    private val dsl = CustomerControllerDsl()

    @Test
    fun `customer is found and returned successfully`() {
        dsl.`a customer exists in the system`()
            .`the customer is requested by ID`()
            .`the response should be OK with customer details`()
            .`the service should have been called to get the customer`()
    }

    @Test
    fun `customer is not found`() {
        dsl.`no customer exists in the system`()
            .`the customer is requested by ID`()
            .`the response should be not found`()
            .`the service should have been called to get the customer`()
    }

    @Test
    fun `a customer is created successfully`() {
        dsl.`a valid customer creation request is provided`()
            .`a request is made to create a new customer`()
            .`the response should be OK with customer details`()
            .`the service should have been called to create the customer`()
    }

    @Test
    fun `a customer is deleted successfully`() {
        dsl.`a customer exists in the system`()
            .`a request is made to delete a customer`()
            .`the response should indicate no content`()
            .`the service should have been called to delete the customer`()
    }
}
