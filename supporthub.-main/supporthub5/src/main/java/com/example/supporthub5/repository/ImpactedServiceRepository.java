package com.example.supporthub5.repository;


import com.example.supporthub5.model.ServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ImpactedServiceRepository extends JpaRepository<ServiceDetails,Long> {

   Optional<ServiceDetails> findByServiceId(long serviceid) ;
}
