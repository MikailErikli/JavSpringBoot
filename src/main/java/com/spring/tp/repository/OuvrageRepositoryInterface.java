package com.spring.tp.repository;

import com.spring.tp.entity.Ouvrage;
import org.springframework.data.repository.CrudRepository;

public interface OuvrageRepositoryInterface extends CrudRepository<Ouvrage, Long> {
}
