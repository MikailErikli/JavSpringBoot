package com.spring.tp.controller;

import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RayonController {
	@Autowired
	RayonRepository repository;

	@GetMapping("/list")
	public List<Rayon> list(){
		List<Rayon> rayons =new ArrayList<>();
		repository.findAll().forEach(rayons::add);
		return rayons;
	}

}
