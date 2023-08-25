package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldCreateBook() {
        Author author = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );
        author = authorRepository.save(author);

        Publisher publisher = new Publisher(
                "Sony Interactive Entertainment",
                "38th Street",
                "Santa Monica",
                "CA",
                "94088",
                "222-222-2222",
                "sony@gmail.com"
        );
        publisher = publisherRepository.save(publisher);

        Book book = new Book(
                "12345",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us",
                publisher.getId(),
                new BigDecimal("9.99")
        );
        book = bookRepository.save(book);
        Optional<Book> bookFromDB = bookRepository.findById(book.getId());
        assertEquals(bookFromDB.get(), book);
    }

    @Test
    public void shouldGetBookById() throws Exception {
        Author author = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );
        author = authorRepository.save(author);

        Publisher publisher = new Publisher(
                "Sony Interactive Entertainment",
                "38th Street",
                "Santa Monica",
                "CA",
                "94088",
                "222-222-2222",
                "sony@gmail.com"
        );
        publisher = publisherRepository.save(publisher);

        Book book = new Book(
                "12345",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us",
                publisher.getId(),
                new BigDecimal("9.99")
        );
        book = bookRepository.save(book);
        Optional<Book> bookFromDB = bookRepository.findById(book.getId());
        assertEquals(bookFromDB.get(), book);
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        Author author = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );
        author = authorRepository.save(author);

        Publisher publisher = new Publisher(
                "Sony Interactive Entertainment",
                "38th Street",
                "Santa Monica",
                "CA",
                "94088",
                "222-222-2222",
                "sony@gmail.com"
        );
        publisher = publisherRepository.save(publisher);

        Book book1 = new Book(
                "12345",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us",
                publisher.getId(),
                new BigDecimal("9.99")
        );

        Book book2 = new Book(
                "54321",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us 2",
                publisher.getId(),
                new BigDecimal("9.99")
        );

        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void shouldUpdateBookById() throws Exception {
        Author author = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );
        author = authorRepository.save(author);

        Publisher publisher = new Publisher(
                "Sony Interactive Entertainment",
                "38th Street",
                "Santa Monica",
                "CA",
                "94088",
                "222-222-2222",
                "sony@gmail.com"
        );
        publisher = publisherRepository.save(publisher);

        Book book = new Book(
                "12345",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us",
                publisher.getId(),
                new BigDecimal("9.99")
        );
        book = bookRepository.save(book);
        book.setPrice(new BigDecimal("4.99"));
        Book updatedBook = bookRepository.save(book);

        Optional<Book> resultBook = bookRepository.findById(book.getId());
        assertEquals(updatedBook, resultBook.get());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        Author author = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );
        author = authorRepository.save(author);

        Publisher publisher = new Publisher(
                "Sony Interactive Entertainment",
                "38th Street",
                "Santa Monica",
                "CA",
                "94088",
                "222-222-2222",
                "sony@gmail.com"
        );
        publisher = publisherRepository.save(publisher);

        Book book = new Book(
                "12345",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us",
                publisher.getId(),
                new BigDecimal("9.99")
        );
        book = bookRepository.save(book);
        bookRepository.deleteById(book.getId());
        assertEquals(false, bookRepository.findById(book.getId()).isPresent());
    }

    @Test
    public void shouldSearchBookByAuthorId() throws Exception {
        Author author = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );
        author = authorRepository.save(author);

        Publisher publisher = new Publisher(
                "Sony Interactive Entertainment",
                "38th Street",
                "Santa Monica",
                "CA",
                "94088",
                "222-222-2222",
                "sony@gmail.com"
        );
        publisher = publisherRepository.save(publisher);

        Book book1 = new Book(
                "12345",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us",
                publisher.getId(),
                new BigDecimal("9.99")
        );

        Book book2 = new Book(
                "54321",
                LocalDate.of(2023, 8, 1),
                author.getId(),
                "The Last of Us 2",
                publisher.getId(),
                new BigDecimal("9.99")
        );

        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);

        List<Book> books = bookRepository.findByAuthorId(author.getId());
        assertEquals(2, books.size());
    }

}
