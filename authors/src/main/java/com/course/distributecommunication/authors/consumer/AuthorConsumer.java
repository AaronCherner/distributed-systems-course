package com.course.distributecommunication.authors.consumer;

import com.course.distributecommunication.authors.dto.BookAndAuthor;
import com.course.distributecommunication.authors.services.AuthorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorConsumer {

    public static final String QUEUE = "queue";

    private final AuthorService authorService;

    @Autowired
    public AuthorConsumer(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RabbitListener(queues = QUEUE)
    public void receiveMessage(BookAndAuthor bookAndAuthor) {
        if (authorService.findById(bookAndAuthor.getAuthorId()) == null) {
            authorService.putAuthor(bookAndAuthor.toAuthor());
            System.out.println("Author: " + bookAndAuthor.toAuthor() + " has added");
        }
        System.out.println("Author: " + bookAndAuthor.toAuthor() + " already exists");
    }
}
