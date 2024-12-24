package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.repository.BookRepository;
import org.example.test_example.PostgresContainer;
import org.example.test_example.dto.BookDto;
import org.example.test_example.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SpringBootServiceTest extends PostgresContainer {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = new Book();
        book.setAuthor("mock author");
        book.setTitle("mock title");

        bookRepository.deleteAll();
        bookRepository.save(book);
    }

    @Test
    @DisplayName("Тест поиска книги по автору")
    void getBookByAuthor() {
        BookDto bookDto = bookService.getBookByAuthor("mock author");

        assertNotNull(bookDto);
        assertEquals("mock author", bookDto.author());
        assertEquals("mock title", bookDto.title());
    }

    @Test
    @DisplayName("Тест на корректность ошибки при поиске книги по автору")
    void getBookByAuthorException() {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> bookService.getBookByAuthor("No_Name_Author"));
    }
}
