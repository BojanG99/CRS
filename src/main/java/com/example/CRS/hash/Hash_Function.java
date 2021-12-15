package com.example.CRS.hash;

import carProject.ECS.dto.CarJobDTO;
import com.firstexample.demo.model.Car;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash_Function {

    public String get_Hash(Car car){
        String hash=car.toString();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(hash.getBytes());
            String stringHash = new String(messageDigest.digest());
            return stringHash;
        }
        catch (NoSuchAlgorithmException e){}
        return null;
    }
    public long get_Hash_long(Car car){
        String toString=car.toString();
        long hash = 7;
        for (int i = 0; i < toString.length(); i++) {
            hash = hash*31 + toString.charAt(i);
        }
    return hash;

    }

}
