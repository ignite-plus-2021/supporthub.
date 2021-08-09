package com.example.supporthub5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="service")
@Data
public class ServiceDetails{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long serviceId;

    @Column(name="impacted_service")
    private String impactedService;

    @Column(name="support_team")
    public String supportTeam;


    @OneToMany(mappedBy = "service",fetch = FetchType.LAZY,  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests ;

}

