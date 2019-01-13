package com.project.library;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.SimpleLease.class)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(View.SimpleLease.class)
    private LibraryUser reader;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonView(View.SimpleLease.class)
    private Book book;
    @JsonView(View.SimpleLease.class)
    private Timestamp expiration_date;
    @CreationTimestamp
    @JsonView(View.SimpleLease.class)
    private Timestamp created_on;
    @JsonView(View.SimpleLease.class)
    private Timestamp return_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LibraryUser getReader() {
        return reader;
    }

    public void setReader(LibraryUser reader) {
        this.reader = reader;
    }

    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Timestamp getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Timestamp created_on) {
        this.created_on = created_on;
    }

    public Timestamp getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
