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
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @PostMapping(path = "/all")
    @JsonView(View.BookWithAuthor.class)
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    Map<String, String> addBook(@RequestBody Book book) {

        book.setLeftCopies(book.getQuantity());
        bookRepository.save(book);
        return ApiResponse.responseOK();
    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    Map<String, String> removeBook(@RequestBody Book book) {

        bookRepository.delete(book.getId());
        return ApiResponse.responseOK();
    }




}
