package com.exaltit.rpncalc.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {

    @Id
    private Long id;

    private List<T> values;

    public Stack(Long id) {
        this.id = id;
    }

    public Stack(Long id, List<T> values) {
        this.id = id;
        this.values = values;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public void addValue(T value) {
        if (this.values == null) {
            this.values = new ArrayList<>();
        }

        this.values.add(value);
    }
}
