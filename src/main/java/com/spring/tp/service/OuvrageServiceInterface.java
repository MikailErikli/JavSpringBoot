package com.spring.tp.service;

import com.spring.tp.entity.Ouvrage;

import java.util.List;

public interface OuvrageServiceInterface {
    List<Ouvrage> getOuvrages();
    void addOuvrage(Ouvrage ouvrage);
}
