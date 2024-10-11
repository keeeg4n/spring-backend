package dev.keeg4n.bookstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookstoreApplication

fun main(args: Array<String>) {
	runApplication<BookstoreApplication>(*args)
}
