package ru.develgame.bookmarks.jsf.model;

import java.util.Objects;

public class Console {
    private int id;

    private String name;

    private Integer price;

    public Console(int id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return id == console.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
