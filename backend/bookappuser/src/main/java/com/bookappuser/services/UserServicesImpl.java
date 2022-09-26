package com.bookappuser.services;

import com.bookappuser.exceptions.NoUserExistException;
import com.bookappuser.exceptions.UserExistException;
import com.bookappuser.exceptions.UserNotFoundException;
import com.bookappuser.model.Users;
import com.bookappuser.repository.UsersRepository;
import com.bookappuser.response.UsersResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements  UserServices{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UsersResponseTemplate<?> addUser(Users users){
        Optional<Users> user = usersRepository.findById(users.getUserEmail());
        if(user.isPresent()){
            throw new UserExistException();
        }
        usersRepository.save(users);
        return new UsersResponseTemplate<Users>(users, HttpStatus.CREATED.value(),HttpStatus.CREATED);
    }

    @Override
    public UsersResponseTemplate<?> updateUser(Users users, String userEmail){
        Optional<Users> optional = usersRepository.findById(userEmail);
        if(optional.isPresent()){
            users.setUserEmail(userEmail);
            usersRepository.save(users);
            return new UsersResponseTemplate<>(users, HttpStatus.FOUND.value(), HttpStatus.FOUND);
        } else {
            throw new NoUserExistException();
        }

    }

    @Override
    public UsersResponseTemplate<?> deleteUsers(String userEmail){
        Optional<Users> optional = usersRepository.findById(userEmail);
        if(optional.isPresent()){
            Users user = optional.get();
            usersRepository.deleteById(userEmail);
            return new UsersResponseTemplate<Users>(user, HttpStatus.FOUND.value(), HttpStatus.FOUND);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UsersResponseTemplate<?> getUsers(String userEmail){
        Optional<Users> optional = usersRepository.findById(userEmail);
        if(optional.isPresent()){
            return new UsersResponseTemplate<Users>(optional.get(), HttpStatus.FOUND.value(), HttpStatus.FOUND);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<String> getAllUsers() {
       return usersRepository.findAllUser();
    }

}
