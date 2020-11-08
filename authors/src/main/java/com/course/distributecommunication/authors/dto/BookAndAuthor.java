package com.course.distributecommunication.authors.dto;

import com.course.distributecommunication.authors.models.Author;

public class BookAndAuthor {
    private int id;
    private String title;
    private int pages;
    private int authorId;
    private String firstName;
    private String lastName;

    public BookAndAuthor(int id, String title, int pages, int authorId, String firstName, String lastName) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public BookAndAuthor() {}

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BookAndAuthor withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public BookAndAuthor withAuthorId(int author) {
        this.setAuthorId(author);
        return this;
    }

    public BookAndAuthor withPages(int pages) {
        this.setPages(pages);
        return this;
    }

    public Author toAuthor() {
        return new Author(id,firstName, lastName);
    }

    @Override
    public String toString() {
        return "BookAndAuthor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
