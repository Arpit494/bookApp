package com.bookappuser.services;

import com.bookappuser.model.Users;
import com.bookappuser.response.UsersResponseTemplate;

import java.util.List;

public interface UserServices {
    public UsersResponseTemplate<?> addUser(Users users);
    public UsersResponseTemplate<?> updateUser(Users users, String userEmail);
    public UsersResponseTemplate<?> deleteUsers(String userEmail);

    public UsersResponseTemplate<?> getUsers(String userEmail);

    public List<String> getAllUsers();
}
