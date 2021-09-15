package com.example.supporthub5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Requests")
@Data
public class Request{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long requestId;


    @Column(name="state")
    private String state;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requester_name", nullable = false)
    private User user;

    @Column(name="description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private ServiceDetails service;


}








