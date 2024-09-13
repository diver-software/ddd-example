package org.diversoftware.dddexample.application.service

import org.diversoftware.dddexample.dsl.CustomerServiceDsl
import org.junit.jupiter.api.Test

class CustomerServiceV1Test {

    private val dsl = CustomerServiceDsl()

    @Test
    fun `a customer is found successfully`() {
        dsl.`a customer exists in the repository`()
            .`the service is asked to get a customer`()
            .`the repository should be called to find the customer`()
    }

    @Test
    fun `no customer is found`() {
        dsl.`no customer exists in the repository`()
            .`the service is asked to get a customer`()
            .`the repository should be called to find the customer`()
    }

    @Test
    fun `a customer is created successfully`() {
        dsl.`a customer is ready to be saved`()
            .`the service is asked to create a customer`()
            .`the repository should be called to save the customer`()
    }

    @Test
    fun `a customer is deleted successfully`() {
        dsl.`a customer exists in the repository`()
            .`the service is asked to delete a customer`()
            .`the repository should be called to delete the customer`()
    }
}
