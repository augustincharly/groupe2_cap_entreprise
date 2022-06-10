package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;

@Controller
@RequestMapping("avis")
public class AvisController {

	@Autowired
	private IAvisService avisService;

	@GetMapping
	public String getAvis(Model model) {
		List<AvisDTO> avisDTOs = avisService.getAvisDTOs();

		model.addAttribute("list_avis", avisDTOs);

		return "avisListe";
	}

	@GetMapping("{id}")
	public String getAvis(@PathVariable(name = "id") Long id, Model model) {
		AvisDTO avis = avisService.getAvisDTO(id);

		model.addAttribute("avis", avis);

		return "avisDetails";
	}

}
