package com.humanbooster.groupe2_cap_entreprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Classification;
import com.humanbooster.groupe2_cap_entreprise.entity.Editeur;
import com.humanbooster.groupe2_cap_entreprise.entity.Genre;
import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;
import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.CreateJeuFormWrapper;
import com.humanbooster.groupe2_cap_entreprise.service.IClassificationService;
import com.humanbooster.groupe2_cap_entreprise.service.IEditeurService;
import com.humanbooster.groupe2_cap_entreprise.service.IGenreService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;
import com.humanbooster.groupe2_cap_entreprise.service.IModeleEconomiqueService;
import com.humanbooster.groupe2_cap_entreprise.service.IPlateformeService;

@Controller
@RequestMapping("moderateur")
public class ModerateurController {

	@Autowired
	IJeuService jeuService;
	
	@Autowired
	private IEditeurService editeurService;
	
	@Autowired
	private IGenreService genreService;
	
	@Autowired
	private IClassificationService classificationService;
	
	@Autowired
	private IModeleEconomiqueService modeleEconomiqueService;
	
	@Autowired
	private IPlateformeService plateformeService;

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
	public ModelAndView delete(@PathVariable(name = "id") Long id) {

		jeuService.delete(id);
		return new ModelAndView("redirect:/moderateur/jeu");
	}
	
	@GetMapping("jeu/new")
	public String getCreateJeu(Model model) {
		List<Editeur> editeurs = editeurService.getAllEditeurs();
		List<Genre> genres = genreService.getAllGenres();
		List<Classification> classifications = classificationService.getAllClassifications();
		List<Plateforme> plateformes = plateformeService.getAllPlateformes();
		List<ModeleEconomique> modeleEconomiques = modeleEconomiqueService.getAllModeleEconomiques();
		model.addAttribute("editeurs", editeurs);
		model.addAttribute("genres", genres);
		model.addAttribute("classifications", classifications);
		model.addAttribute("plateformes", plateformes);
		model.addAttribute("modeleEconomiques", modeleEconomiques);
		model.addAttribute("createJeu", new CreateJeuFormWrapper());
		return "moderateur/createJeu";
	}
	
	@PostMapping("jeu/new")
	public ModelAndView postCreateJeu(@ModelAttribute CreateJeuFormWrapper createjeu) {
		jeuService.save(createjeu);
		return new ModelAndView("redirect:/moderateur/jeu");
	}
	
}
