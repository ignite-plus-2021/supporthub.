package com.example.supporthub5.model;


import lombok.Data;

import javax.persistence.*;

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

//    @OneToMany(mappedBy = "ServiceDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Requests requests ;

}

