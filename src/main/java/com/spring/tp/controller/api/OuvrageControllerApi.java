package com.spring.tp.controller.api;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.service.OuvrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ouvrage")
public class OuvrageControllerApi {
	@Autowired
	OuvrageService ouvrageService;


	@GetMapping
	public List<Ouvrage> loadOuvrage(){
		return ouvrageService.getOuvrages();
	}

	@PostMapping
	public Ouvrage createOuvrage(@RequestBody Ouvrage ouvrage) {
		ouvrageService.addOuvrage(ouvrage);

		return ouvrage;
	}

}
