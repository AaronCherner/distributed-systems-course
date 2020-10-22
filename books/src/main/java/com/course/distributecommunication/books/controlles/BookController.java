package com.course.distributecommunication.books.controlles;

import com.course.distributecommunication.books.dto.BookDto;
import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Collection<BookDto>> findAll() {
        return ResponseEntity.ok(bookService.getBooks().stream()
                .map(Book::toDto)
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }
}