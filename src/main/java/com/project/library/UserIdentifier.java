package com.project.library;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.library.UserRepository;
import com.project.library.LibraryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserIdentifier {

    @Autowired
    private UserRepository userRepository;

    public LibraryUser Identify(String Token) {
        Token = Token.replaceFirst("Bearer ", "");
        DecodedJWT decodedtoken = JWT.decode(Token);
        String login = decodedtoken.getSubject();
        return userRepository.getByLogin(login);
    }
}