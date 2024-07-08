package com.example.kopringplayground

import com.example.kopringplayground.blog.BlogProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class KopringPlaygroundApplication

fun main(args: Array<String>) {
    // SpringApplication.run(KopringPlaygroundApplication::class.java, *args)의 idiomatic alternative
    runApplication<KopringPlaygroundApplication>(*args)
}
