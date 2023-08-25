package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AuthorRepositoryTests {

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
    public void shouldCreateAuthor() throws Exception {
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
        Optional<Author> authorFromDB = authorRepository.findById(author.getId());
        assertEquals(authorFromDB.get(), author);
    }

    @Test
    public void shouldGetAuthorById() throws Exception {
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
        Optional<Author> authorFromDB = authorRepository.findById(author.getId());
        assertEquals(authorFromDB.get(), author);
    }

    @Test
    public void shouldGetAllAuthors() throws Exception {
        Author author1 = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );

        Author author2 = new Author(
                "Nice",
                "Cat",
                "43th Street",
                "San Francisco",
                "CA",
                "88049",
                "999-999-9999",
                "ncat@gmail.com"
        );

        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);
        List<Author> authors = authorRepository.findAll();
        assertEquals(2, authors.size());
    }

    @Test
    public void shouldUpdateAuthorById() throws Exception {
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
        author.setPhone("123-456-7890");
        Author updatedAuthor = authorRepository.save(author);

        Optional<Author> resultAuthor = authorRepository.findById(author.getId());
        assertEquals(updatedAuthor, resultAuthor.get());
    }

    @Test
    public void shouldDeleteAuthorById() throws Exception {
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
        authorRepository.deleteById(author.getId());
        assertEquals(false, authorRepository.findById(author.getId()).isPresent());
    }

}
