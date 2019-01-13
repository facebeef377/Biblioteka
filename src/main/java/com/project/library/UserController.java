package com.project.library;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.library.UserRepository;
import com.project.library.LibraryUser;

import static com.project.library.SecurityConstants.HEADER_STRING;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
    UserIdentifier identifier;
    @Autowired
    UserRepository users;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.users = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    //Lista wszystkich uzytkownikow
    @PostMapping(path = "/all")
    public @ResponseBody
    Iterable<LibraryUser> getAllUser() {
        return users.findAll();
    }
    
    //Rejestracja
    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = "application/json")
    public @ResponseBody
    Map<String, String> register(@RequestBody LibraryUser user) {
        Map<String, String> result = new HashMap<String, String>();
        boolean loginTaken = !(users.getByLogin(user.getLogin()) == null);
        if (!loginTaken) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            users.save(user);
            result.put("Status", "OK");
            return result;
        } else {
        	result.put("Status", "FAILED_LOGIN");
            return result;
        }
    }
    
    //Zwraca użytkownika na podstawie tokenu
    @PostMapping(path = "/identify")
    public @ResponseBody
    String identify(@RequestHeader(HEADER_STRING) String token) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(identifier.Identify(token));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(path = "/delete")
    public @ResponseBody
    Map<String, String> removeBook(@RequestBody LibraryUser libraryUser) {

        users.delete(libraryUser.getId());
        return ApiResponse.responseOK();
    }

}