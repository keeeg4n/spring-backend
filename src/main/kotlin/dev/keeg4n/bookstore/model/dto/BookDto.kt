package dev.keeg4n.bookstore.model.dto

data class BookDto(
    val id: Long,
    val title: String,
    val description: String,
    val image: String,
    val author: AuthorDto
)