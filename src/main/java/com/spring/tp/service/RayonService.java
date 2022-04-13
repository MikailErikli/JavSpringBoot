package com.spring.tp.service;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RayonService implements RayonServiceInterface{

    @Autowired
    RayonRepositoryInterface repository;

    public List<Rayon> getRayons() {
        return (List<Rayon>) repository.findAll();
    }

    public Rayon getRayonById(Integer id){
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    @Transactional
    public void addRayon(Rayon rayon) {
        repository.save(rayon);
    }

    public String deleteRayonById(Integer id){
        repository.deleteById(Long.valueOf(id));
        return "le Rayon est supprimé";
    }

    public Rayon updateRayon(Rayon rayon){
       Rayon oldRayon=null;
        Optional<Rayon> optionalrayon=repository.findById(rayon.getId());
        if(optionalrayon.isPresent()) {
            oldRayon=optionalrayon.get();
            oldRayon.setTheme(rayon.getTheme());
            repository.save(oldRayon);
        }else {
            return new Rayon();
        }
        return oldRayon;
    }

}
