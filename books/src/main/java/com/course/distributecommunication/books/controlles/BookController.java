package com.course.distributecommunication.books.controlles;

import com.course.distributecommunication.books.dto.BookAndAuthor;
import com.course.distributecommunication.books.dto.BookDto;
import com.course.distributecommunication.books.models.Book;
import com.course.distributecommunication.books.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.course.distributecommunication.books.Application.EXCHANGE;
import static com.course.distributecommunication.books.Application.ROUTING_KEY;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;
    private final RabbitTemplate template;

    @Autowired
    public BookController(
            BookService bookService,
            RabbitTemplate template) {
        this.bookService = bookService;
        this.template = template;
    }

    @GetMapping
    public ResponseEntity<Collection<BookDto>> findAll() {
        return ResponseEntity.ok(bookService.getBooks().stream()
                .map(Book::toDto)
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @PutMapping
    public ResponseEntity<String> putBook(@RequestBody BookAndAuthor bookAndAuthor) {
        bookService.putBook(bookAndAuthor.toBook());
        template.convertAndSend(EXCHANGE, ROUTING_KEY, bookAndAuthor);
        return new ResponseEntity<>("The book has put successfully", HttpStatus.OK);
    }
}