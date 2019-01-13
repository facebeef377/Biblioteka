package com.project.library;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.SimpleAuthor.class)
    private Integer id;
    @JsonView(View.SimpleAuthor.class)
    private String name;
    @JsonView(View.SimpleAuthorWithBook.class)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}