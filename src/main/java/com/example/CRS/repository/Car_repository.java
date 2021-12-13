package com.example.CRS.repository;

import com.example.CRS.model.Car_Hash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Car_repository extends JpaRepository<Car_Hash, String>{
    Car_Hash getCar_HashById_hash(String hash);
    Car_Hash getCar_HashById_hashAndEmail(String hash,String email);



}
