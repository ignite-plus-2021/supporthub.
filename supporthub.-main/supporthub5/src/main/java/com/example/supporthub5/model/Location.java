package com.example.supporthub5.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;


@Entity
@Table(name="Location")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Location {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long locationId;

    @Column(name="building_name")
    private String buildingName;

    @Column(name="floor")
    public String floor;


}