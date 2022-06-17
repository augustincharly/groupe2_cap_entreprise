package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.entity.Joueur;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.AvisJeuFormWrapper;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.FilterWrapper;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;
import com.humanbooster.groupe2_cap_entreprise.service.IJoueurService;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;
import com.humanbooster.groupe2_cap_entreprise.utils.PaginatedList;
import com.humanbooster.groupe2_cap_entreprise.utils.Utils;

@Controller
@RequestMapping("joueur")
public class JoueurController {

	@Autowired
	private IJeuService jeuService;

	@Autowired
	private IAvisService avisService;

	@Autowired
	private IJoueurService joueurService;

	@GetMapping("avis/page/{id}")
	public String viewPage(Model model, @PathVariable(name = "id") Integer pageNum,
			@RequestParam(defaultValue = "jeu", name = "sortField") String sortField,
			@RequestParam(defaultValue = "asc", name = "sortDir") String sortDir,
			@RequestParam(defaultValue = "none", name = "jeuFilter") String jeuId,
			@RequestParam(defaultValue = "none", name = "joueurFilter") String joueurId,
			@RequestParam(defaultValue = "none", name = "moderated") String statut) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String thispseudojoueur = authentication.getName().toString();
		List<Avis> avisListe = avisService.getAllAvisSorted(thispseudojoueur, sortField, sortDir);
		if (!jeuId.equals("none")) {
			avisListe = avisListe.stream().filter(avis -> avis.getJeu().getId() == Utils.stringToLong(jeuId))
					.collect(Collectors.toList());
		}
		if (!joueurId.equals("none")) {
			avisListe = avisListe.stream().filter(avis -> avis.getJoueur().getId() == Utils.stringToLong(joueurId))
					.collect(Collectors.toList());
		}
		if (!statut.equals("none")) {
			if (statut.equals("true")) {
				avisListe = avisListe.stream().filter(avis -> avis.getModerateur() != null)
						.collect(Collectors.toList());
			} else {
				avisListe = avisListe.stream().filter(avis -> avis.getModerateur() == null)
						.collect(Collectors.toList());
			}

		}
		List<AvisDTO> avisDTOsToDisplay = new ArrayList<>();
		PaginatedList<Avis> paginatedList = new PaginatedList<Avis>(avisListe);
		List<Avis> pageEnCours = paginatedList.getPage(pageNum + 1);
		for (Avis avisToAdd : pageEnCours) {
			avisDTOsToDisplay.add(TransformerFactory.getAvisTransformer().transform(avisToAdd));
		}

		List<JeuDTO> jeux = jeuService.getAllJeuDTOs();
		List<Joueur> joueurs = joueurService.getAllJoueurs();
		model.addAttribute("jeux", jeux);

		model.addAttribute("joueurs", joueurs);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", paginatedList.numberOfPages());
		model.addAttribute("totalItems", paginatedList.getTotalItems());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("jeuId", jeuId);
		model.addAttribute("joueurId", joueurId);
		model.addAttribute("statutFilter", statut);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("list_avis", avisDTOsToDisplay);
		model.addAttribute("filterWrapper", new FilterWrapper());

		return "joueur/avisListe";
	}

	@PostMapping("avis/page/{id}")
	public ModelAndView viewPagePost(Model model, @PathVariable(name = "id") Integer pageNum,
			@ModelAttribute FilterWrapper filterWrapper) {
		Long jeuId = filterWrapper.getJeu_id();
		Long joueurId = filterWrapper.getJoueur_id();
		boolean statut = filterWrapper.getModerated();
		ModelAndView modelAndView = new ModelAndView("redirect:/joueur/avis/page/0?sortField=jeu&sortDir=asc&jeuFilter="
				+ jeuId + "&joueurFilter=" + joueurId + "&moderated=" + statut);
		return modelAndView;
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
