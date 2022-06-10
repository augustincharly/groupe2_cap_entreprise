package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;

@Controller
@RequestMapping("jeu")
public class JeuController {

	@Autowired
	IJeuService jeuService;

	@GetMapping
	public String getJeux(Model model) {
		List<JeuDTO> jeux = jeuService.getJeux();

		model.addAttribute("jeux", jeux);

		return "jeuxListe";
	}

}
