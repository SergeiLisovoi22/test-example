package org.example.service;

import org.example.repository.BookRepository;
import org.example.test_example.dto.BookDto;
import org.example.test_example.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = new Book();
        book.setAuthor("mock author");
        book.setTitle("mock title");

        when(bookRepository.findByAuthor(any())).thenReturn(Optional.of(book));
    }

    @Test
    @DisplayName("Тест поиска книги по автору")
    void getBookByAuthor() {
        BookService bookService = new BookService(bookRepository);
        BookDto bookDto = bookService.getBookByAuthor("Shumilin");
        assertNotNull(bookDto);
        assertEquals("mock author", bookDto.author());
        assertEquals("mock title", bookDto.title());
    }


}