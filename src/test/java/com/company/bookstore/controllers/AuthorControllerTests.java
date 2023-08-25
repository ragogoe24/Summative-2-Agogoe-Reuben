package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    AuthorRepository authorRepository;

    @BeforeEach
    public void setUp() {}

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

        String inputJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                        post("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAuthorById() throws Exception {
        mockMvc.perform(
                        get("/authors/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllAuthors() throws Exception {
        mockMvc.perform(
                        get("/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
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

        String inputJson = mapper.writeValueAsString(author);
        mockMvc.perform(
                        put("/authors/1")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAuthorById() throws Exception {
        mockMvc.perform(
                        delete("/authors/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
