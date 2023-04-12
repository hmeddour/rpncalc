package com.exaltit.rpncalc.domain;

import java.util.List;

public class Person {
    private final String name;

    private final int age;

    private final List<Book> books;

    public Person(String name, int age, List<Book> books) {
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Book> getBooks() {
        return books;
    }
}
