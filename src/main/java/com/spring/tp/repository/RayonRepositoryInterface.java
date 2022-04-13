package com.spring.tp.repository;

import com.spring.tp.entity.Rayon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RayonRepositoryInterface extends CrudRepository<Rayon, Long> {

    List<Rayon> findBytheme(String theme);
}
