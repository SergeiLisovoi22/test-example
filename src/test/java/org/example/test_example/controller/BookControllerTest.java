package org.example.test_example.controller;

import org.example.repository.BookRepository;
import org.example.test_example.PostgresContainer;
import org.example.test_example.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BookControllerTest extends PostgresContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        bookRepository.deleteAll();
        saveTestBooks();
    }

    private void saveTestBooks() {
        Book book1 = new Book();
        book1.setAuthor("someAuthor");
        book1.setTitle("Skazka");

        Book book2 = new Book();
        book2.setAuthor("Tolstoy");
        book2.setTitle("Mock title");

        bookRepository.saveAll(Arrays.asList(book1, book2));
    }

    @Test
    @DisplayName("Тест запроса на поиск книги по автору")
    void getBookByAuthor() throws Exception {
        mockMvc.perform(get("/v1/book/author")
                        .param("author", "someAuthor"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(("$.author"), is("someAuthor")));
    }


}