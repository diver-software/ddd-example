package org.diversoftware.dddexample.domain.model

import java.util.*

data class Customer(
    val id: UUID,
    var name: String,
    var email: String
)
