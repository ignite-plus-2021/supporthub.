package com.example.supporthub5.model;


import lombok.Data;

import javax.persistence.*;

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

//    @OneToMany(mappedBy = "Location", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   // private Request requests ;

}