package com.example.supporthub5.repository;


import com.example.supporthub5.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RequestRepository  extends JpaRepository<Request,Long> {
//    List<Request> findByUserName(String Username);
}
