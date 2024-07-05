package com.example.kopringplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KopringPlaygroundApplication

fun main(args: Array<String>) {
    // SpringApplication.run(KopringPlaygroundApplication::class.java, *args)의 idiomatic alternative
    runApplication<KopringPlaygroundApplication>(*args)
}
