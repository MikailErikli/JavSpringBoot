package com.spring.tp.controller.web;

import com.spring.tp.entity.Rayon;
import com.spring.tp.service.RayonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rayon")
public class RayonControllerWeb {
	@Autowired
	RayonService rayonService;

	@GetMapping("/")
	public String loadRayon(){
		List<Rayon> Rayons = rayonService.getRayons();
		return "rayon/listRayon";
	}

	@GetMapping("/create")
	public String createRayon(Rayon rayon) {
		rayonService.addRayon(rayon);
		return "";
	}

}
