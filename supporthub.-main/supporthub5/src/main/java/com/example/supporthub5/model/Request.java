package com.example.supporthub5.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Requests")
@Data
public class Request{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long requestId;


    @Column(name="state")
    private String state;


    @JsonProperty("userId")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property = "id",scope=User.class)
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


    @Column(name="description")
    private String description;

    @JsonProperty("locationId")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property = "locationId",scope=User.class)
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private Location location;



    @JsonProperty("serviceId")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property = "serviceId",scope=User.class)
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private ServiceDetails service;

    @Temporal(TemporalType.DATE)
    @Column(name="created_on")
    private Date CreatedOn=new Date(System.currentTimeMillis());


    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;

private void setLocation(long  locationId)
{
    Location loc =new Location();
    loc.setLocationId(locationId);
    this.location=loc;
}


    private void setUser(long userId)
    {
        User user=new User();
        user.setId(userId);
        this.user=user;
    }



    private void setService(long serviceId)
    {
        ServiceDetails sDetails=new ServiceDetails();
        sDetails.setServiceId(serviceId);
        this.service=sDetails;
    }
}





