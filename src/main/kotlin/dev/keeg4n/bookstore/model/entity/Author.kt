package dev.keeg4n.bookstore.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "authors")
data class Author(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val age: Int,
    val description: String,
    val image: String
)