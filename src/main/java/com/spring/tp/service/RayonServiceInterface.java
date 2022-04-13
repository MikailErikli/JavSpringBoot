package com.spring.tp.service;

import com.spring.tp.entity.Rayon;

import java.util.List;

public interface RayonServiceInterface {

    List<Rayon> getRayons();
    void addRayon(Rayon rayon);

}
