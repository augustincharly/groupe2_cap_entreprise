package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.service.AvisService;
import com.humanbooster.groupe2_cap_entreprise.service.JeuService;

@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
	private AvisService avisService;

	@Autowired
	private JeuService jeuService;

	@GetMapping("avis")
	public List<AvisDTO> getAvis() {
		return avisService.getAvisDTOs();
	}

	@GetMapping("jeux")
	public List<JeuDTO> getJeux() {
		return jeuService.getAllJeuDTOs();
	}

}
