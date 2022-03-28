package com.example.demopostalcode.repositories;

import com.example.demopostalcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.postalCode >= :#{#from} and u.postalCode <= :#{#to}")
    List<User> findUsersByPOstalCodeRange(@Param("from") int  from, @Param("to") int to);
}
