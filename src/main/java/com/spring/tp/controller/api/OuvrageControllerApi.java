package com.spring.tp.controller.api;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.service.OuvrageService;
import com.spring.tp.service.OuvrageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ouvrage")
public class OuvrageControllerApi {
	@Autowired
	OuvrageServiceInterface ouvrageService;


	@GetMapping
	public List<Ouvrage> loadOuvrage(){
		return ouvrageService.getOuvrages();
	}

	@GetMapping("/{id}")
	public Ouvrage getOuvrageByid(@PathVariable Integer id) {
		return ouvrageService.getOuvrageById(id);
	}


	@PostMapping
	public Ouvrage createOuvrage(@RequestBody Ouvrage ouvrage) {
		ouvrageService.addOuvrage(ouvrage);

		return ouvrage;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String delete(@PathVariable(name="id") Integer id){
		return ouvrageService.deleteOuvrageById(id);
	}


	@PutMapping
	public Ouvrage updateOuvrage(@RequestBody Ouvrage ouvrage){
		return ouvrageService.updateOuvrage(ouvrage);
	}

}
