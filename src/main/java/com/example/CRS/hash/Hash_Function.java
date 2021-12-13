package com.example.CRS.hash;

import carProject.ECS.dto.CarJobDTO;
import com.firstexample.demo.model.Car;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash_Function {

    public String get_Hash(Car car){
        String hash=car.toString()+car.getEngineType().toString()+car.getChassis().toString();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(hash.getBytes());
            String stringHash = new String(messageDigest.digest());
            return stringHash;
        }
        catch (NoSuchAlgorithmException e){}
        return null;
    }

}
