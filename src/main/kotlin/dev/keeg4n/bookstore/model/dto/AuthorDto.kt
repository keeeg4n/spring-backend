package dev.keeg4n.bookstore.model.dto

data class AuthorDto(
    val id: Long?,
    val name: String,
    val description: String,
    val age: Int,
    var image: String
)