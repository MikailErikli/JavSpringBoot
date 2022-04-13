package com.spring.tp.entity;

import javax.persistence.*;

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

    @ManyToOne
    private Rayon rayon;




}


