package com.spring.tp.service;

import com.spring.tp.entity.Rayon;

import java.util.List;

public interface RayonServiceInterface{

    List<Rayon> getRayons() throws Exception;
    void addRayon(Rayon rayon) throws Exception ;

    Rayon getRayonById(Integer id) throws Exception;

    String deleteRayonById(Integer id) throws Exception;

    Rayon updateRayon(Rayon rayon) throws Exception;


}
