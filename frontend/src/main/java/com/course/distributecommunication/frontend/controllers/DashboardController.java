package com.course.distributecommunication.frontend.controllers;

import com.course.distributecommunication.frontend.dto.AuthorDto;
import com.course.distributecommunication.frontend.dto.BookDto;
import com.course.distributecommunication.frontend.models.Dashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    private final RestTemplate restTemplate;

    private static final String AUTHORS_URL = "http://authors:8080/api/v1/authors";
    private static final String BOOKS_URL = "http://books:8080/api/v1/books";

    @Autowired
    public DashboardController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        ResponseEntity<List<AuthorDto>> authorsResponse = restTemplate.exchange(
                AUTHORS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AuthorDto>>() {}
        );

        ResponseEntity<List<BookDto>> booksResponse = restTemplate.exchange(
                BOOKS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookDto>>() {}
                );

        List<AuthorDto> authorsList = authorsResponse.getBody();
        List<BookDto> booksList = booksResponse.getBody();

        Dashboard dashboard = new Dashboard(authorsList, booksList);
        return ResponseEntity.ok(dashboard);
    }
}