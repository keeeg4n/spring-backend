package dev.keeg4n.bookstore.service.impl

import dev.keeg4n.bookstore.model.entity.Author
import dev.keeg4n.bookstore.repository.AuthorRepository
import dev.keeg4n.bookstore.service.AuthorService
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(private val authorRepository: AuthorRepository) : AuthorService {

    override fun save(author: Author): Author {
        return authorRepository.save(author)
    }

}
