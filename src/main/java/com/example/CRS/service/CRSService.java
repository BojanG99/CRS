package com.example.CRS.service;


import carProject.ECS.dto.CarJobDTO;
import com.example.CRS.hash.Hash_Function;
import com.example.CRS.model.Car_Hash;
import com.example.CRS.repository.Car_repository;
import com.example.Jobs.model.Job;
import com.firstexample.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class CRSService {
    @Autowired
    private Car_repository car_repository;
    @KafkaListener(topics = "car_consumer")
    public void getFromKafka(CarJobDTO carjob) throws UnsupportedEncodingException {
        if(check_for_duplicates(carjob.getCar(),carjob.getJob())){

            //send mail to user



            //add to database
            Car_Hash new_car_hash=new Car_Hash();
            String hash=new Hash_Function().get_Hash(carjob.getCar());
            new_car_hash.setId_hash(hash);
            new_car_hash.setEmail(carjob.getJob().getName_of_site());
            car_repository.save(new_car_hash);
        }
    }
    public boolean check_for_duplicates(Car car, Job job){
        String hash=new Hash_Function().get_Hash(car);
        return car_repository.getCar_HashById_hashAndEmail(hash,job.getName_of_site())!=null;


    }

}
