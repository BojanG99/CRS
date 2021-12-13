package com.example.CRS.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name= "CAR")
public class Car_Hash {

    @Id
    @Column(name = "id")
    private String id_hash;

    @Column(name="email")
    private String email;
}
