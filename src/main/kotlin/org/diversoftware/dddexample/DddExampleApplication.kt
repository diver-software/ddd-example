package org.diversoftware.dddexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DddExampleApplication

fun main(args: Array<String>) {
    runApplication<DddExampleApplication>(*args)
}
