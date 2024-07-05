package com.example.kopringplayground.study

import com.example.kopringplayground.blog.format
import com.example.kopringplayground.blog.toSlug
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ExtensionTest {
    @Test
    fun `String tuSlug test`() {
        val str = "Hello, World"
        val expected = "hello-world"
        val actual = str.toSlug()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `LocalDateTime format test`() {
        val date = LocalDateTime.of(2021, 9, 1, 0, 0, 0)
        val expected = "2021-09-01 1st 2021"
        val actual = date.format()
        assertThat(actual).isEqualTo(expected)
    }
}
