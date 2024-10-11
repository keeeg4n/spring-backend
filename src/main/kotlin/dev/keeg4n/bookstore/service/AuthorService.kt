package dev.keeg4n.bookstore.service

import dev.keeg4n.bookstore.model.entity.Author

interface AuthorService {

    fun save(author: Author): Author

}