package com.spring.tp.controller.api;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.service.RayonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rayon")
public class RayonControllerApi {
	@Autowired
	RayonService rayonService;


	@GetMapping
	public List<Rayon> loadRayon(){
		return rayonService.getRayons();
	}

	@GetMapping("/{id}")
	public Rayon getOuvrageByid(@PathVariable Integer id) {
		return rayonService.getRayonById(id);
	}

	@PostMapping
	public void createRayon(@RequestBody Rayon rayon) {
		rayonService.addRayon(rayon);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name="id") Integer id){
		rayonService.deleteRayonById(id);
	}

	@PutMapping
	public Rayon updateRayon(@RequestBody Rayon rayon){
		return rayonService.updateRayon(rayon);
	}

}
