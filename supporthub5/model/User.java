package com.example.supporthub5.model;


import lombok.Data;
import lombok.Value;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
@Table(name="Employees")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private Long Id;


    @Column(name="first_name" , nullable=false)
    @NotEmpty(message="First name can not be empty")
    private String firstName;

    @Column(name="last_name")
    private String lastName;


    @NotEmpty(message = "User name cannot be empty")
    @Column(name="user_name")
    private String userName;


    @NotEmpty(message = "Password can not be empty")
    @Column(name="password")
    private String password;

    @Column(name="email_id"  ,nullable=false )
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Please provide a valid email id")
    private String emailId;

    @NotEmpty(message = "PhoneNo can not be empty")
    @Column(name="phone_no")
    private String phoneNo;

    @NotEmpty(message = "role  can not be empty")
    @Column(name="role")
    private String role;



//    @Column(name="status" ,  columnDefinition = "integer default 0")
    private  Integer loggedIn;



}

