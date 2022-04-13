package com.spring.tp.service;

import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RayonService implements RayonServiceInterface{

    @Autowired
    RayonRepository repository;

    public List<Rayon> getRayons() {
        return (List<Rayon>) repository.findAll();
    }

    @Transactional
    public void addRayon(Rayon rayon) {
        repository.save(rayon);
    }
}
