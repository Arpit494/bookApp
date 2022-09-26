package com.bookappuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @Column(name = "email")
    private String userEmail;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String userPassword;

//    @Column(name = "image")
//    private String userImage = null;

}
