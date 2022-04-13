package com.spring.tp.controller.api;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.service.OuvrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@GetMapping("/{id}")
	public Ouvrage getOuvrageByid(@PathVariable Integer id) {
		return ouvrageService.getOuvrageById(id);
	}


	@PostMapping
	public void createOuvrage(@RequestBody Ouvrage ouvrage) {
		ouvrageService.addOuvrage(ouvrage);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String delete(@PathVariable(name="id") Integer id){
		return ouvrageService.deleteOuvrageById(id);
	}

}
