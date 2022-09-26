package com.bookappuser.controller;

import com.bookappuser.model.JwtRequest;
import com.bookappuser.model.JwtResponse;
import com.bookappuser.model.Users;
import com.bookappuser.response.UsersResponseTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    public UsersResponseTemplate<?> addUser(@RequestBody() Users user);
    public UsersResponseTemplate<?> updateUser(@RequestBody() Users user, @PathVariable() String userEmail);
    public UsersResponseTemplate<?> deleteUser(@PathVariable() String userEmail);
    public UsersResponseTemplate<?> getUsers(@PathVariable() String userEmail);
    public List<String> getAllUsers();
    public JwtResponse login(@RequestBody() JwtRequest jwtRequest) throws Exception;
}
