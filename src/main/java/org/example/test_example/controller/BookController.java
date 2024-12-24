package org.example.test_example.controller;

import org.example.service.BookService;
import org.example.test_example.dto.BookDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/author")
    public BookDto getByAuthor(@RequestParam("author") String author) {
        return bookService.getBookByAuthor(author);
    }

    @GetMapping("/title")
    public BookDto getByTitle(@RequestParam("title") String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }
}
