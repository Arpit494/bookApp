package com.bookappuser.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseTemplate<E> {

    private E response;
    private Integer statusCode;
    private HttpStatus message;

}
