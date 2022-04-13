package com.spring.tp.service;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.OuvrageRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OuvrageService {
    @Autowired
    OuvrageRepositoryInterface repository;

    public List<Ouvrage> getOuvrages() {
        return (List<Ouvrage>) repository.findAll();
    }

    public Ouvrage getOuvrageById(Integer id){
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Transactional
    public void addOuvrage(Ouvrage ouvrage) {
        repository.save(ouvrage);
    }

    public String deleteOuvrageById(Integer id){
        repository.deleteById(Long.valueOf(id));
        return "l'ouvrage' est supprimé";
    }

//    public Ouvrage updateOuvrage(Ouvrage ouvrage){
//        Ouvrage oldOuvrage = null;
//        Optional<Ouvrage> optionalOuvrage=repository.findById(ouvrage.getId());
//        if(optionalOuvrage.isPresent()) {
//            oldOuvrage=optionalOuvrage.get();
//            oldOuvrage.setAuthor(ouvrage.getAuthor());
//            oldOuvrage.setTitle(ouvrage.getTitle());
//            oldOuvrage.setIsbn(ouvrage.getIsbn());
//            oldOuvrage.setPrice(ouvrage.getPrice());
//            oldOuvrage.setStock(ouvrage.getStock());
//            oldOuvrage.setRayon(ouvrage.getRayon());
//            repository.save(oldOuvrage);
//        }else {
//            return new Ouvrage();
//        }
//        return oldOuvrage;
//    }


}
