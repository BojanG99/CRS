package com.example.CRS.repository;

import com.example.CRS.model.Car_Hash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface Car_repository extends JpaRepository<Car_Hash, String>{

    @Query("select ch from Car_Hash ch where ch.id_hash= :id and ch.email=:email")
    Collection<Car_Hash> getCar_HashByHash(@Param("id")long hash,@Param("email")String email);


}
