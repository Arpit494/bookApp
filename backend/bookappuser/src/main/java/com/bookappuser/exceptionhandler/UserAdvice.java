package com.bookappuser.exceptionhandler;

import com.bookappuser.exceptions.InvalidCredentials;
import com.bookappuser.exceptions.NoUserExistException;
import com.bookappuser.exceptions.UserExistException;
import com.bookappuser.exceptions.UserNotFoundException;
import com.bookappuser.response.UsersResponseTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public UsersResponseTemplate<String> handleUserExist(UserExistException userExistException){
        return new UsersResponseTemplate<String>("User already exist", HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoUserExistException.class)
    public UsersResponseTemplate<String> handleNoUserExist(NoUserExistException noUserExistException){
        return new UsersResponseTemplate<String>("No such user exist", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public UsersResponseTemplate<String> handleUserNotFound(UserNotFoundException userNotFoundException){
        return new UsersResponseTemplate<String>("User not found", HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public UsersResponseTemplate<String> handleInvalidCredentials(InvalidCredentials invalidCredentials){
        return new UsersResponseTemplate<String>("Invalid credentials", HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED);
    }
}
