package com.bookappuser.services;

import com.bookappuser.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUsersDetailsServices implements UserDetailsService {

    @Autowired
    UserServicesImpl userServices;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = (Users) userServices.getUsers(username).getResponse();
        if(user == null){
            throw new UsernameNotFoundException("Username invalid");
        }
        return new User(username,user.getUserPassword(), new ArrayList<>());
    }
}
