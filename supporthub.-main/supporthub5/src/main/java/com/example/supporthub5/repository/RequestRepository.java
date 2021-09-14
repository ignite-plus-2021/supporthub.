package com.example.supporthub5.repository;


import com.example.supporthub5.model.Location;
import com.example.supporthub5.model.Request;
import com.example.supporthub5.model.ServiceDetails;
import com.example.supporthub5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface RequestRepository  extends JpaRepository<Request,Long> {
    
    Optional<List<Request>> findByUser(User user);
   
    Optional<Request> findByRequestId(Long id);

    @Query(nativeQuery=true,value="SELECT  * from requests  WHERE (request_id=?1 or ?1 is null)    AND 
                                                                  (location_id=?2 or ?2 is null)   AND
                                                                  (service_id=?3 or ?3 is null)    AND 
                                                                  (description=?4 or ?4 is null )  AND  
                                                                  (state=?5 or ?5 is null)         AND
                                                                 (created_on=?6 or ?6 is null) ")
    Optional<List<Request>> applyFilters ( Long requestId, Long
                                           locationId, Long ServiceId, String description, String state,String createdOn);


}



