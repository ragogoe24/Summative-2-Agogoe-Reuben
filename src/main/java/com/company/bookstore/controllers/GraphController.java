package com.company.bookstore.controllers;
import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @QueryMapping
    public Publisher findPublisherById(@Argument int id){
        Optional<Publisher> returnVal = publisherRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

    @QueryMapping
    public Author findAuthorById(@Argument int id){
        Optional<Author> returnVal = authorRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

    @QueryMapping
    public Book findBookById(@Argument int id){
        Optional<Book> returnVal = bookRepository.findById(id);
        return returnVal.isPresent() ? returnVal.get(): null;
    }

}