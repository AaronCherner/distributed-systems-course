package com.course.distributecommunication.frontend.models;

import com.course.distributecommunication.frontend.dto.AuthorDto;
import com.course.distributecommunication.frontend.dto.BookDto;
import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    private List<AuthorDto> authors;
    private List<BookDto> books;

    public Dashboard(List<AuthorDto> authors, List<BookDto> books) {
        this.authors = authors;
        this.books = books;
    }

    public Dashboard() {
        authors = new ArrayList<>();
        books = new ArrayList<>();
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}