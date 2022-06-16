package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.AvisJeuFormWrapper;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;

@Controller
@RequestMapping("joueur")
public class JoueurController {

	@Autowired
	private IJeuService jeuService;

	@Autowired
	private IAvisService avisService;

	@RequestMapping("avis/page/{id}")
	public String viewPage(Model model, @PathVariable(name = "id") Integer pageNum,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String thispseudojoueur = authentication.getName().toString();
		if (sortField == null) {
			sortField = "jeu";
		}
		if (sortDir == null) {
			sortDir = "asc";
		}
		List<AvisDTO> avisDTOs = new ArrayList<>();
		Page<Avis> page = avisService.getAllPageAvisSorted(thispseudojoueur, pageNum, sortField, sortDir);
		List<Avis> avis = avisService.getAllPageAvisSorted(thispseudojoueur, pageNum, sortField, sortDir).getContent();
		for (Avis avisToAdd : avis) {
			avisDTOs.add(TransformerFactory.getAvisTransformer().transform(avisToAdd));
		}
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("list_avis", avisDTOs);

		return "joueur/avisListe";
	}

	@GetMapping("avis/new")
	public String getCreateAvis(Model model) {
		List<Jeu> jeux = jeuService.getAllJeux();
		Avis avis = new Avis();
		List<Float> notes = new ArrayList<>();
		for (int i = 0; i <= 40; i++) {
			notes.add((float) i / 2);
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
		avisService.saveNewAvis(avisjeu.getAvis_description(), avisjeu.getAvis_note(), jeu);
		return new ModelAndView("redirect:/joueur/avis/page/0");
	}

	@GetMapping("avis/{id}")
	public String getAvis(@PathVariable(name = "id") Long id, Model model) {
		AvisDTO avis = avisService.getAvisDTO(id);

		model.addAttribute("avis", avis);

		return "joueur/avisDetails";
	}

}
