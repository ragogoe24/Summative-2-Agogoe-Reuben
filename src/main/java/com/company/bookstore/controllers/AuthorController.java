package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;


    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> resultAuthor = authorRepository.findById(id);
        return resultAuthor.isPresent() ? resultAuthor.get() : null;
    }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PutMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthorById(@PathVariable int id, @RequestBody Author updatedAuthor) {
        Optional<Author> resultAuthor = authorRepository.findById(id);
        if (resultAuthor.isPresent()) {
            Author returnVal = resultAuthor.get();
            updatedAuthor.setId(returnVal.getId());
            authorRepository.save(updatedAuthor);
        }
    }

    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorById(@PathVariable int id) {
        Optional<Author> resultAuthor = authorRepository.findById(id);
        if (resultAuthor.isPresent()) { authorRepository.deleteById(id); }
    }


}
