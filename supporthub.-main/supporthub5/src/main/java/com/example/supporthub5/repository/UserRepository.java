package com.example.supporthub5.repository;

import com.example.supporthub5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public  interface UserRepository extends JpaRepository<User,Long> {

     Optional<User> findById(Long userId) ;
     User findByUserNameAndPassword(String userName, String password);
     User findByEmailId(String emailId);
     User findByUserName(String userName);
}