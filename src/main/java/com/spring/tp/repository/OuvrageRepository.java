package com.spring.tp.repository;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import org.springframework.data.repository.CrudRepository;

public interface OuvrageRepository extends CrudRepository<Ouvrage, Long> {
}
