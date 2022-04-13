package com.spring.tp.service;

import com.spring.tp.entity.Ouvrage;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OuvrageServiceInterface{
    List<Ouvrage> getOuvrages();
    void addOuvrage(Ouvrage ouvrage);

    Ouvrage getOuvrageById(Integer id);

    String deleteOuvrageById(Integer id);

    Ouvrage updateOuvrage(Ouvrage ouvrage);

}
