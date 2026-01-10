package org.springboot.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springboot.librarymanagement.dao.BookDao;
import org.springboot.librarymanagement.entity.Book;
import org.springboot.librarymanagement.entity.ResponseStructure;
import org.springboot.librarymanagement.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;

//	Add Book
	public ResponseEntity<ResponseStructure<Book>> addBook(Book book) {
		ResponseStructure<Book> structure = new ResponseStructure<>();
		structure.setData(bookDao.addBook(book));
		structure.setMessage("Book Added Sucessfully");
		structure.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
	}

//	Get All Books
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBook() {
		ResponseStructure<List<Book>> structure = new ResponseStructure<>();
		List<Book> recBook = bookDao.getAllBook();
		if (!recBook.isEmpty()) {
			structure.setMessage(" Books Found ");
			structure.setData(recBook);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.OK);
		}

		// if no books found, throw custom exception
		throw new BookNotFoundException("No books found in the library");

	}

//	Get Book by ID
	public ResponseEntity<ResponseStructure<Optional<Book>>> getBookById(int id) {
		ResponseStructure<Optional<Book>> structure = new ResponseStructure<>();
		Optional<Book> recBook = bookDao.getBookById(id);
		if (!recBook.isEmpty()) {
			structure.setMessage("Books Found By your Id ");
			structure.setData(recBook);
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Optional<Book>>>(structure, HttpStatus.ACCEPTED);
		}
		throw new BookNotFoundException("Books not found with your id: " + id);
	}

//	Update Book by ID
	public ResponseEntity<ResponseStructure<Book>> updateBookById(Book book, int id) {
		ResponseStructure<Book> structure = new ResponseStructure<>();
		Optional<Book> recBook = bookDao.getBookById(id);

		if (recBook.isPresent()) {
			Book existBook = recBook.get();

			// Update the fields
			existBook.setTitle(book.getTitle());
			existBook.setAuthor(book.getAuthor());
			existBook.setPublisher(book.getPublisher());
			existBook.setIsbn(book.getIsbn());
			existBook.setCategory(book.getCategory());
			existBook.setAvailableCopies(book.getAvailableCopies());
			existBook.setPublishedDate(book.getPublishedDate());
			existBook.setTotalCopies(book.getTotalCopies());

			// Save the updated book
			Book updatedBook = bookDao.addBook(existBook);

			structure.setMessage("Book updated successfully with ID: " + id);
			structure.setData(updatedBook);
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<>(structure, HttpStatus.OK);
		}

		throw new BookNotFoundException("Book not found with ID: " + id);
	}

//	Delete Book by ID
	public ResponseEntity<ResponseStructure<String>> deleteBookById(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Book> recBook = bookDao.getBookById(id);
		if (recBook.isPresent()) {
			bookDao.deleteById(id);
			structure.setMessage("Book Dleted By Id : " + id);
			structure.setData("Deleted successfully");
			structure.setStatusCode(HttpStatus.NO_CONTENT.value());
		}
		throw new BookNotFoundException("Books not found with your id: " + id);
	}

}
