package com.project.library;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.library.Author;

import javax.persistence.*;
import java.util.List;
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.SimpleBook.class)
    private Integer id;
    @JsonView(View.SimpleBook.class)
    private String title;
    @ManyToOne
    @JoinColumn(name = "book_author_id")
    @JsonView(View.BookWithAuthor.class)
    private Author author;
    @JsonView(View.SimpleBook.class)
    private Integer quantity;
    @JsonView(View.SimpleBook.class)
    private Integer leftCopies;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Lease> leases;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    public Integer getLeftCopies() {
        return leftCopies;
    }

    public void setLeftCopies(Integer leftCopies) {
        this.leftCopies = leftCopies;
    }

    public void borrow()
    {
        leftCopies--;
    }
    public void giveBack()
    {
        leftCopies++;
    }

}
