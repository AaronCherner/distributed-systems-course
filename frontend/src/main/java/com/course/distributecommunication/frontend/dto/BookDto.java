package com.course.distributecommunication.frontend.dto;

public class BookDto {
    private int id;
    private String title;
    private int pages;
    private int authorId;

    public BookDto(int id) {
        this.id = id;
    }

    public BookDto() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int autorId) {
        this.authorId = autorId;
    }

    public BookDto withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public BookDto withAuthorId(int author) {
        this.setAuthorId(author);
        return this;
    }

    public BookDto withPages(int pages) {
        this.setPages(pages);
        return this;
    }
}