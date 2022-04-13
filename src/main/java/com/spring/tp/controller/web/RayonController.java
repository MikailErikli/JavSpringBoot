package com.spring.tp.controller.web;

import com.spring.tp.entity.Rayon;
import com.spring.tp.service.RayonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rayon")
public class RayonController {
	@Autowired
	RayonService rayonService;


	@GetMapping
	public List<Rayon> loadRayon(){
		return rayonService.getRayons();
	}

	@PostMapping
	public void createRayon(@RequestBody Rayon rayon) {
		rayonService.addRayon(rayon);
	}

}
