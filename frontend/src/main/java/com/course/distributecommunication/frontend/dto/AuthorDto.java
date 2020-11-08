package com.course.distributecommunication.frontend.dto;

public class AuthorDto {
    private int id;
    private String firstName;
    private String lastName;

    public AuthorDto(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorDto() {}

    public int getId() {
        return id;
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

    public AuthorDto withFirstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public AuthorDto withLastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }
}
