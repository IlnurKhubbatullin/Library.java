package ru.maxima.springmvc.models;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Name should not to be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "The field cannot be empty")
    private String author;
    @Min(value = 0, message = "Age should be more than 0")
    private int age;

    private String owner;

    public Book() {
    }

    public Book(int id, String name, String author, int age, String owner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.age = age;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
