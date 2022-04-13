package com.spring.tp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ouvrage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id ;

    private String title ;
    private String author;
    private String isbn;
    private Float price;
    private Integer stock;
    private Rayon rayon ;




}


