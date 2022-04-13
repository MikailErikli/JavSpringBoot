package com.spring.tp.service;

import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RayonService implements RayonServiceInterface{

    @Autowired
    RayonRepositoryInterface repository;

    public List<Rayon> getRayons() {
        return (List<Rayon>) repository.findAll();
    }

    @Transactional
    public void addRayon(Rayon rayon) {
        repository.save(rayon);
    }
}
