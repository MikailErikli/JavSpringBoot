package com.spring.tp.controller.api;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.service.OuvrageService;
import com.spring.tp.service.RayonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ouvrage")
public class OuvrageController {
	@Autowired
	OuvrageService ouvrageService;


	@GetMapping
	public List<Ouvrage> loadOuvrage(){
		return ouvrageService.getOuvrages();
	}

	@PostMapping
	public void createOuvrage(@RequestBody Ouvrage ouvrage) {
		ouvrageService.addOuvrage(ouvrage);
	}

}
