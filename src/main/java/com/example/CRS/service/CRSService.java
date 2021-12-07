package com.example.CRS.service;


import com.firstexample.demo.model.Car;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class CRSService {

    @KafkaListener(topics = "car_consumer")
    public void getFromKafka(Car CarJobDTO) throws UnsupportedEncodingException {

    }

}
