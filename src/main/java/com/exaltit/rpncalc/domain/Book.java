package com.exaltit.rpncalc.domain;

public class Book implements Comparable<Book> {

    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Book o) {
        return 0;
    }
}
