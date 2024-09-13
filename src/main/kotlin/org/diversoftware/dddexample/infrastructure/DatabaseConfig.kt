package org.diversoftware.dddexample.infrastructure

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = [
    "org.diversoftware.dddexample.infrastructure.persistence"
])
class DatabaseConfig
