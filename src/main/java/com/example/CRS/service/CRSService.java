package com.example.CRS.service;


import carProject.ECS.dto.CarJobDTO;
import com.example.CRS.hash.Hash_Function;
import com.example.CRS.mail.builder.EMailBuilder;
import com.example.CRS.model.Car_Hash;
import com.example.CRS.repository.Car_repository;
import com.example.Jobs.model.Job;
import com.firstexample.demo.model.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

@Service
public class CRSService {
    @Autowired
    private Car_repository car_repository;
    @Autowired
    private EmailSenderService email_sender;

    @Value("${from.email}")
    private String from_email;

    @KafkaListener(topics = "car_consumer")
    public void getFromKafka(CarJobDTO carjob) throws UnsupportedEncodingException {
        if(check_for_duplicates(carjob.getCar(),carjob.getJob())){

            //send mail to user
        email_sender.sendSimpleEmail(from_email,carjob.getJob().getEmail(), new EMailBuilder().make_Body(carjob.getCar()),"Novi automobili");
        System.out.println("poslato "+carjob.getJob().getEmail());
        System.out.println("poruka je" + new EMailBuilder().make_Body(carjob.getCar()));
            //add to database
            Car_Hash new_car_hash=new Car_Hash();
            long hash=new Hash_Function().get_Hash_long(carjob.getCar());
            new_car_hash.setId_hash(hash);
            new_car_hash.setEmail(carjob.getJob().getName_of_site());
            car_repository.save(new_car_hash);
        }
        else{
            System.out.println("Nije poslato "+carjob.getJob().getEmail());
            System.out.println("poruka je" + new EMailBuilder().make_Body(carjob.getCar()));
        }
    }
    public boolean check_for_duplicates(Car car, Job job){
        long hash=new Hash_Function().get_Hash_long(car);
        Collection<Car_Hash> kolekcija=car_repository.getCar_HashByHash(hash);
        if(kolekcija.size()==0)return true;
        return false;


    }

}
