package com.example.supporthub5.model;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name="Employees")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private Long Id;


    @Column(name="first_name" , nullable=false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="password")
    private String password;

    @Column(name="email_id"  ,nullable=true )
    private String emailId;

    @Column(name="phone_no")
    private String phoneNo;
}