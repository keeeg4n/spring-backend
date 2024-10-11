package dev.keeg4n.bookstore.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val title: String,

    val description: String,

    val image: String,

    @ManyToOne(cascade = [CascadeType.DETACH])
    @JoinColumn(name = "author_id")
    val author: Author

)
