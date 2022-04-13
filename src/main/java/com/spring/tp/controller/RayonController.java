package com.spring.tp.controller;

import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepository;
import com.spring.tp.service.RayonService;
import com.spring.tp.service.RayonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rayon")
public class RayonController {
	@Autowired
	RayonRepository repository;
	private RayonServiceInterface rayonService;

	@GetMapping("/list")
	public List<Rayon> list(){
		List<Rayon> rayons = new ArrayList<>();
		repository.findAll().forEach(rayons::add);
		return rayons;
	}

	@GetMapping
	public List<Rayon> loadRayon(){
		return rayonService.getRayons();
	}

	@PostMapping
	public void createRayon(@RequestBody Rayon rayon) {
		rayonService.addRayon(rayon);
	}

}
