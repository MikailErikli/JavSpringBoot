package com.spring.tp.controller.web;

import com.spring.tp.entity.Ouvrage;
import com.spring.tp.service.OuvrageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ouvrage")
public class OuvrageControllerWeb {
	@Autowired
	OuvrageService ouvrageService;

	@GetMapping("/")
	public String loadOuvrage(){
		List<Ouvrage> ouvrages = ouvrageService.getOuvrages();
		return "ouvrage/listOuvrage";
	}

	@GetMapping("/create")
	public String createOuvrage(Ouvrage ouvrage) {
		ouvrageService.addOuvrage(ouvrage);
		return "";
	}

}
