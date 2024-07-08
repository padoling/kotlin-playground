package com.example.kopringplayground.blog

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class HttpControllersTests(
    @Autowired val mockMvc: MockMvc,
) {
    // lateinit은 변수의 초기화를 지연시켜준다.
    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val johnDoe = User("johnDoe", "John", "Doe")
        val lorem5Article =
            Article(
                title = "Lorem",
                headline = "Lorem",
                content = "dolor sit amet",
                author = johnDoe,
            )
        val ipsumArticle =
            Article(
                title = "Ipsum",
                headline = "Ipsum",
                content = "dolor sit amet",
                author = johnDoe,
            )
        // every는 Mockk 라이브러리의 함수로, 어떤 함수가 호출될 때마다 지정된 값을 반환한다.
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(lorem5Article, ipsumArticle)

        mockMvc
            .perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[0].slug").value(lorem5Article.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[1].slug").value(ipsumArticle.slug))
    }

    @Test
    fun `List users`() {
        val johnDoe = User("johnDoe", "John", "Doe")
        val janeDoe = User("janeDoe", "Jane", "Doe")
        every { userRepository.findAll() } returns listOf(johnDoe, janeDoe)
        mockMvc
            .perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[1].login").value(janeDoe.login))
    }
}
