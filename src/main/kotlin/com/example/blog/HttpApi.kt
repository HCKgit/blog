/* ----------------------------------------------------------------------------
From https://spring.io/guides/tutorials/spring-boot-kotlin/
Development fo Digital Ocean Server Test and Kotlin project piloting

For tests, instead of integration tests we choose to leverage
@WebMvcTest and @MockBean to test only the web layer. (HttpApiTests.kt)

MehStuff.com & HCK copyright 2018
---------------------------------------------------------------------------- */


package com.example.blog

import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository,
                        private val markdownConverter: MarkdownConverter) {

    @GetMapping("/")
    fun findAll() = repository.findAllByOrderByAddedAtDesc()

    @PostMapping("/")
    fun createArticle(@Valid @RequestBody article: Article) : Article =
            repository.save(article)

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long, @RequestParam converter: String?) = when (converter) {
        "markdown" -> repository.findById(id).map { it.copy(
                headline = markdownConverter.invoke(it.headline),
                content = markdownConverter.invoke(it.content)) }
        null -> repository.findById(id)
        else -> throw IllegalArgumentException("Only markdown converter is supported")
    }
}

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String) = repository.findById(login)
}