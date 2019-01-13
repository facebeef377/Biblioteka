package com.project.library;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(path = "/author")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping(path = "/all")
    @JsonView(View.SimpleAuthorWithBook.class)
    public @ResponseBody
    Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    Map<String, String> addAuthor(@RequestBody Author author) {

        authorRepository.save(author);
        return ApiResponse.responseOK();
    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    Map<String, String> removeAuthor(@RequestBody Author author) {

        authorRepository.delete(author.getId());
        return ApiResponse.responseOK();
    }

}
