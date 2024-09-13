package org.diversoftware.dddexample.infrastructure.persistence.repository

import java.util.UUID
import org.diversoftware.dddexample.infrastructure.persistence.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaCustomerRepository : JpaRepository<CustomerEntity, UUID>