package org.diversoftware.dddexample.api.controller
import java.util.*
import org.diversoftware.dddexample.api.dto.CustomerDto
import org.diversoftware.dddexample.api.dto.toDomain
import org.diversoftware.dddexample.api.dto.toDto
import org.diversoftware.dddexample.domain.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController( // TODO: use an interface?
    private val customerService: CustomerService
) {

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: UUID): ResponseEntity<CustomerDto> {
        val customer = customerService.get(id)
        return if (customer != null) {
            ResponseEntity.ok(customer.toDto())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<CustomerDto> {
        val savedCustomer = customerService.create(customerDto.toDomain())
        return ResponseEntity.ok(savedCustomer.toDto())
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: UUID): ResponseEntity<Void> {
        customerService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
