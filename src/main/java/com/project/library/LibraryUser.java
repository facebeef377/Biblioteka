package com.project.library;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class LibraryUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.SimpleUser.class)
    private Integer id;
    @JsonView(View.SimpleUser.class)
    private String login;
    private String password;
    @JsonView(View.SimpleUser.class)
    private String name;
    @JsonView(View.SimpleUser.class)
    private String type;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Lease> reader;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Lease> getReader() {
        return reader;
    }

    public void setReader(List<Lease> reader) {
        this.reader = reader;
    }
}
