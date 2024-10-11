package dev.keeg4n.bookstore.utils

import dev.keeg4n.bookstore.model.dto.AuthorDto
import dev.keeg4n.bookstore.model.entity.Author

fun Author.toAuthorDto(): AuthorDto = AuthorDto(
        id=this.id,
        name=this.name,
        description=this.description,
        age=this.age,
        image=this.image
)

fun AuthorDto.toAuthor(): Author = Author(
    id=this.id,
    name=this.name,
    description=this.description,
    age=this.age,
    image=this.image
)

