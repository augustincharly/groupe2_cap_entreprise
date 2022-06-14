package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;
import com.humanbooster.groupe2_cap_entreprise.repository.AvisRepository;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;
import com.humanbooster.groupe2_cap_entreprise.service.IModerateurService;

@Controller
@RequestMapping("moderateur")
public class ModerateurController {

	@Autowired
	private IJeuService jeuService;

	@Autowired
	private IAvisService avisService;

	@Autowired
	private IModerateurService moderateurService;

	@GetMapping("avis")
	public String getAvis(Model model) {
		List<AvisDTO> avisDTOs = avisService.getAvisDTOs();

		model.addAttribute("list_avis", avisDTOs);

		return "moderateur/avisListe";
	}

	@GetMapping("avis/{id}")
	public String getAvis(@PathVariable(name = "id") Long id, Model model) {
		AvisDTO avis = avisService.getAvisDTO(id);

		model.addAttribute("avis", avis);

		return "moderateur/avisDetails";
	}

	@GetMapping("avis/{id}/delete")
	public ModelAndView deleteAvis(@PathVariable(name = "id") Long id) {

		avisService.delete(id);
		return new ModelAndView("redirect:/moderateur/avis");
	}

	@GetMapping("avis/{id}/validate")
	public ModelAndView validateAvis(@PathVariable(name = "id") Long id) {
		Avis avis = avisService.getAvis(id);
		Moderateur moderateur = moderateurService.getModerateur(4l);
		avisService.validateAvis(avis, moderateur);
		return new ModelAndView("redirect:/moderateur/avis");
	}

	@GetMapping("jeu")
	public String getJeux(Model model) {
		List<JeuDTO> jeux = jeuService.getJeux();

		model.addAttribute("jeux", jeux);

		return "moderateur/jeuxListe";
	}

	@GetMapping("jeu/{id}")
	public String getJeu(@PathVariable(name = "id") Long id, Model model) {
		JeuDTO jeu = jeuService.getJeuDTO(id);

		model.addAttribute("jeu", jeu);

		return "moderateur/jeuDetails";
	}

	@GetMapping("jeu/{id}/delete")
	public ModelAndView deleteJeu(@PathVariable(name = "id") Long id) {

		jeuService.delete(id);
		return new ModelAndView("redirect:/moderateur/jeu");
	}

}
