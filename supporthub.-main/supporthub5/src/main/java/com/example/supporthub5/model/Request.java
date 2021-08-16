package com.example.supporthub5.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

//
//    @Column(name = "photo", length = 1000)
//    private byte[] photo;
//
//    @Column(name = "document", length = 1000)
//    private byte[] document;





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





