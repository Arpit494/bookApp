package com.bookappuser.controller;

import com.bookappuser.model.JwtRequest;
import com.bookappuser.model.JwtResponse;
import com.bookappuser.model.Users;
import com.bookappuser.repository.UsersRepository;
import com.bookappuser.response.UsersResponseTemplate;
import com.bookappuser.services.MyUsersDetailsServices;
import com.bookappuser.services.UserServices;
import com.bookappuser.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
public class UserControllerImpl implements UserController{

    @Autowired
    private UserServices userServices;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUsersDetailsServices myUsersDetailsServices;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    @PostMapping("/register")
    public UsersResponseTemplate<?> addUser(@RequestBody() Users user) {
        return userServices.addUser(user);
    }

    @Override
    @PutMapping("/updateProfile/{userEmail}")
    public UsersResponseTemplate<?> updateUser(@RequestBody() Users user, @PathVariable String userEmail) {
        return userServices.updateUser(user, userEmail);
    }

    @Override
    @DeleteMapping("/removeUser/{userEmail}")
    public UsersResponseTemplate<?> deleteUser(@PathVariable  String userEmail) {
        return userServices.deleteUsers(userEmail);
    }

    @Override
    @GetMapping("/{userEmail}")
    public UsersResponseTemplate<?> getUsers(@PathVariable String userEmail) {
        return userServices.getUsers(userEmail);
    }

    @Override
    @GetMapping("/getAllUsers")
    public List<String> getAllUsers() {
        return userServices.getAllUsers();
    }

    @Override
    @PostMapping("/login")
    public JwtResponse login(@RequestBody() JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserEmail(),
                            jwtRequest.getUserPassword()
                    )
            );
        } catch(BadCredentialsException e) {
            throw new Exception("Invalid credentials", e);
        }

        UserDetails userDetails = myUsersDetailsServices.loadUserByUsername(jwtRequest.getUserEmail());
        String token = jwtUtility.getJwtToken(userDetails);
        String userName = usersRepository.findById(jwtRequest.getUserEmail()).get().getUserEmail();

        return new JwtResponse(token,userName);
    }
}
