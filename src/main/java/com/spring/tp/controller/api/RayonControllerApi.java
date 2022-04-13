package com.spring.tp.controller.api;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.entity.Rayon;
import com.spring.tp.repository.RayonRepositoryInterface;
import com.spring.tp.service.RayonService;
import com.spring.tp.service.RayonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rayon")
public class RayonControllerApi {
	@Autowired
	RayonServiceInterface rayonService;

	@Autowired
	RayonRepositoryInterface rpo;



	@GetMapping
	public List<Rayon> loadRayon() throws Exception {
		return rayonService.getRayons();
	}

	@GetMapping("/{id}")
	public Rayon getOuvrageByid(@PathVariable Integer id) throws Exception {
		return rayonService.getRayonById(id);
	}

	@GetMapping("/findbytheme")
	public ResponseEntity<List<Rayon>> getRayonsByName(@RequestParam String theme) {
		return new ResponseEntity<List<Rayon>>(rpo.findBytheme(theme), HttpStatus.OK);
	}


	@PostMapping
	public void createRayon(@RequestBody Rayon rayon) throws Exception {
		rayonService.addRayon(rayon) ;
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name="id") Integer id) throws Exception{
		rayonService.deleteRayonById(id);
	}

	@PutMapping
	public Rayon updateRayon(@RequestBody Rayon rayon) throws Exception{
		return rayonService.updateRayon(rayon);
	}

}
