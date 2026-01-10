package org.springboot.librarymanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springboot.librarymanagement.entity.Book;
import org.springboot.librarymanagement.entity.ResponseStructure;
import org.springboot.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	// Add Book
	@PostMapping(value = "/saveBooks")
	public ResponseEntity<ResponseStructure<Book>> addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	// Get All Books
	@GetMapping(value = "/getBooks")
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		return bookService.getAllBook();
	}

	// Get Book by ID
	@GetMapping("/book/{id}")
	public ResponseEntity<ResponseStructure<Optional<Book>>> getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}

	// Update Book by ID
	@PutMapping("book/{id}")
	public ResponseEntity<ResponseStructure<Book>> updateBookById(@RequestBody Book book, @PathVariable int id) {
		return bookService.updateBookById(book, id);
	}

	// Delete Book by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBookById(@PathVariable int id) {
		return bookService.deleteBookById(id);
	}
}
