package com.course.distributecommunication.authors.controlles;

import com.course.distributecommunication.authors.dto.AuthorDto;
import com.course.distributecommunication.authors.models.Author;
import com.course.distributecommunication.authors.services.AuthorService;
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
@RequestMapping("api/v1/authors")
public class AuthorController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Collection<AuthorDto>> findAll() {
        return ResponseEntity.ok(authorService.getAuthors().stream()
                .map(Author::toDto)
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }
}