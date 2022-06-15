package com.humanbooster.groupe2_cap_entreprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.humanbooster.groupe2_cap_entreprise.entity.Joueur;
import com.humanbooster.groupe2_cap_entreprise.service.JoueurService;


@Controller
@RequestMapping(value="/inscription")
public class InscriptionController {
	
	@Bean({"b2"})
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private JoueurService joueurService;
	
	@PostMapping
	public String inscription(Model model) {
		Joueur joueur=new Joueur();
		model.addAttribute("joueur", joueur);
		return "inscription";
	}
	
	@PostMapping(value="/valide")
	public ModelAndView insciptionValide(Joueur joueur) {
		joueur.setMotDePasse(passwordEncoder().encode(joueur.getMotDePasse()));
		joueurService.save(joueur);
		return new ModelAndView("redirect:/joueur/avis");
	}

}
