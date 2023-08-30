package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
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

    private Author author1;
    private Author author2;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
        author1 = new Author(
                "Naughty",
                "Dog",
                "34th Street",
                "San Jose",
                "CA",
                "94088",
                "111-111-1111",
                "ndog@gmail.com"
        );
        author2 = new Author(
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
    }

    @Test
    public void shouldCreateAuthor() throws Exception {
        author1 = authorRepository.save(author1);
        Optional<Author> authorFromDB = authorRepository.findById(author1.getId());
        assertEquals(authorFromDB.get(), author1);
    }

    @Test
    public void shouldGetAuthorById() throws Exception {
        Optional<Author> authorFromDB = authorRepository.findById(author1.getId());
        assertEquals(authorFromDB.get(), author1);
    }

    @Test
    public void shouldGetAllAuthors() throws Exception {
        List<Author> authors = authorRepository.findAll();
        assertEquals(2, authors.size());
    }

    @Test
    public void shouldUpdateAuthorById() throws Exception {
        author1.setPhone("123-456-7890");
        Author updatedAuthor = authorRepository.save(author1);

        Optional<Author> resultAuthor = authorRepository.findById(author1.getId());
        assertEquals(updatedAuthor, resultAuthor.get());
    }

    @Test
    public void shouldDeleteAuthorById() throws Exception {
        authorRepository.deleteById(author1.getId());
        assertEquals(false, authorRepository.findById(author1.getId()).isPresent());
    }

}
