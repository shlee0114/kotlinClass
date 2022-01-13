package com.example.basicspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BasicSpringApplication

fun main(args: Array<String>) {
    runApplication<BasicSpringApplication>(*args)
}
