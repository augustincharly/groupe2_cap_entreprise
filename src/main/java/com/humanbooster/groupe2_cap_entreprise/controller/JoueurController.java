package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.humanbooster.groupe2_cap_entreprise.configuration.EnvironmentVariable;
import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.AvisJeuFormWrapper;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;

@Controller
@RequestMapping("joueur")
public class JoueurController {

	@Autowired
	private IJeuService jeuService;
	
	@Autowired
	private IAvisService avisService;

	@GetMapping("avis/page/{id}")
	public String getAvis(@PathVariable(name="id") Integer id, Model model) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String thispseudojoueur = authentication.getName().toString();
		Pageable pagination = PageRequest.of(id, EnvironmentVariable.ITEMS_PER_PAGE);
		List<AvisDTO> avisDTOs = avisService.getAvisDTOsWithPagination(thispseudojoueur, pagination);	
		Integer nombreDePages = avisService.getAvisPageDTOsWithPagination(thispseudojoueur, pagination).getSize();
		model.addAttribute("nombreDePages", nombreDePages);
		model.addAttribute("id", id);
		model.addAttribute("list_avis", avisDTOs);
		return "joueur/avisListe";
	}

	
	@GetMapping("avis/new")
	public String getCreateAvis(Model model) {
		List<Jeu> jeux = jeuService.getAllJeux();
		Avis avis = new Avis();
		List<Float> notes= new ArrayList<>();
		for(int i =0;i<=40;i++) {
			notes.add((float)i/2);
		}
		model.addAttribute("jeux", jeux);
		model.addAttribute("avis", avis);
		model.addAttribute("notes", notes);
		model.addAttribute("jeux_avis", new AvisJeuFormWrapper());
		return "joueur/createAvis";
	}
	
	@PostMapping("avis/new")
	public ModelAndView postCreateAvis(@ModelAttribute AvisJeuFormWrapper avisjeu) {
		Jeu jeu = jeuService.getJeuByID(avisjeu.getJeu_id());
		avisService.saveNewAvis(avisjeu.getAvis_description(), avisjeu.getAvis_note(),jeu);
		return new ModelAndView("redirect:/joueur/avis");
	}



	@GetMapping("avis/{id}")
	public String getAvis(@PathVariable(name = "id") Long id, Model model) {
		AvisDTO avis = avisService.getAvisDTO(id);

		model.addAttribute("avis", avis);

		return "joueur/avisDetails";
	}
	
}
