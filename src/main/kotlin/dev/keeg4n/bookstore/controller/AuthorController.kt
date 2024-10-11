package dev.keeg4n.bookstore.controller

import dev.keeg4n.bookstore.model.dto.AuthorDto
import dev.keeg4n.bookstore.model.entity.Author
import dev.keeg4n.bookstore.service.AuthorService
import dev.keeg4n.bookstore.utils.toAuthor
import dev.keeg4n.bookstore.utils.toAuthorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/author")
class AuthorController(private val authorService: AuthorService) {

    @PostMapping(path = ["create"])
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        val author: Author = authorService.save(authorDto.toAuthor());
        return ResponseEntity.status(HttpStatus.OK).body(author.toAuthorDto())
    }

}
