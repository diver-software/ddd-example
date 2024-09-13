package org.diversoftware.dddexample.infrastructure.persistence.repository

import java.util.*
import org.diversoftware.dddexample.domain.model.Customer
import org.diversoftware.dddexample.domain.repository.CustomerRepository
import org.diversoftware.dddexample.infrastructure.persistence.entity.toDomain
import org.diversoftware.dddexample.infrastructure.persistence.entity.toEntity
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryV1(
    private val jpaRepository: JpaCustomerRepository
) : CustomerRepository {

    override fun findById(id: UUID): Customer? {
        return jpaRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun save(customer: Customer): Customer {
        val entity = customer.toEntity()
        return jpaRepository.save(entity).toDomain()
    }

    override fun deleteById(id: UUID) {
        jpaRepository.deleteById(id)
    }
}
