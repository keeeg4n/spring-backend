package dev.keeg4n.bookstore.repository

import dev.keeg4n.bookstore.model.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long?>