package com.spring.tp.service;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.repository.OuvrageRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OuvrageService implements OuvrageServiceInterface {
    @Autowired
    OuvrageRepositoryInterface repository;

    public List<Ouvrage> getOuvrages() {
        return (List<Ouvrage>) repository.findAll();
    }

    @Transactional
    public void addOuvrage(Ouvrage ouvrage) {
        repository.save(ouvrage);
    }
}
