package com.example.supporthub5.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Location")
@Data
public class Location {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long locationId;

    @Column(name="building_name")
    private String buildingName;

    @Column(name="floor")
    public String floor;


    @OneToMany(mappedBy = "location",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests ;

}