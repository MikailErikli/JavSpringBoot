package com.spring.tp.service;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("RayonServiceInterface")
public class RayonService implements RayonServiceInterface{

    @Autowired
    RayonRepositoryInterface repository;
    @Autowired
    private JdbcTemplate template;


    public List<Rayon> getRayons() throws Exception {
        return (List<Rayon>) repository.findAll();
    }

    public Rayon getRayonById(Integer id) throws Exception{
        return repository.findById(Long.valueOf(id)).orElse(null);
    }


    @Transactional
    public void addRayon(Rayon rayon) throws Exception {
        repository.save(rayon);
    }

    @Transactional
    public String deleteRayonById(Integer id) throws Exception{
        repository.deleteById(Long.valueOf(id));
        return "le Rayon est supprimé";
    }

    public Rayon updateRayon(Rayon rayon) throws Exception{
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
