package com.example.supporthub5.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Service")
@Data
public class ServiceDetails{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long serviceId;

    @Column(name="impacted_service")
    private String impactedService;

    @Column(name="support_team")
    public String supportTeam;


}

