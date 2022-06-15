package com.humanbooster.groupe2_cap_entreprise.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.humanbooster.groupe2_cap_entreprise.configuration.EnvironmentVariable;
import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;
import com.humanbooster.groupe2_cap_entreprise.service.IModerateurService;
import com.humanbooster.groupe2_cap_entreprise.entity.Classification;
import com.humanbooster.groupe2_cap_entreprise.entity.Editeur;
import com.humanbooster.groupe2_cap_entreprise.entity.Genre;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;
import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.JeuFormWrapper;
import com.humanbooster.groupe2_cap_entreprise.service.IClassificationService;
import com.humanbooster.groupe2_cap_entreprise.service.IEditeurService;
import com.humanbooster.groupe2_cap_entreprise.service.IGenreService;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;
import com.humanbooster.groupe2_cap_entreprise.service.IModeleEconomiqueService;
import com.humanbooster.groupe2_cap_entreprise.service.IPlateformeService;

@Controller
@RequestMapping("moderateur")
public class ModerateurController {

	private final String UPLOADED_FOLDER = "D:\\Mes documents\\Mon code\\eclipse-workspace\\groupe2_cap_entreprise\\groupe2_cap_entreprise\\src\\main\\resources\\static\\";
	private final String UPLOADED_DIR = "/";

	@Autowired
	private IJeuService jeuService;

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
	@Autowired
	private IAvisService avisService;

	@Autowired
	private IModerateurService moderateurService;

	@GetMapping("avis/page/{page}")
	public String getAvis(@PathVariable(name="page")Integer numPage, Model model) {
		Pageable pagination = PageRequest.of(numPage, EnvironmentVariable.ITEMS_PER_PAGE);
		List<AvisDTO> avisDTOs = avisService.getAvisDTOsWithPagination(pagination);	
		Integer nombreDePages = avisService.getAvisPageDTOsWithPagination(pagination).getTotalPages();
		model.addAttribute("nombreDePages", nombreDePages);
		model.addAttribute("id", numPage);
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
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String pseudo = authentication.getName().toString();
		Moderateur moderateur = moderateurService.getModerateurByPseudo(pseudo);
		avisService.validateAvis(avis, moderateur);
		return new ModelAndView("redirect:/moderateur/avis");
	}

	@GetMapping("jeu/page/{id}")
	public String getJeux(@PathVariable(name="id") Integer id, Model model) {
		Pageable pagination = PageRequest.of(id, EnvironmentVariable.ITEMS_PER_PAGE);
		List<JeuDTO> jeuxDTOs = jeuService.getJeuDTOsWithPagination(pagination);	
		Integer nombreDePages = jeuService.getJeuPageDTOsWithPagination(pagination).getTotalPages();
		model.addAttribute("nombreDePages", nombreDePages);
		model.addAttribute("id", id);
		model.addAttribute("jeux", jeuxDTOs);
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
		model.addAttribute("createJeu", new JeuFormWrapper());
		return "moderateur/createJeu";
	}

	@PostMapping("jeu/new")
	public ModelAndView postCreateJeu(@ModelAttribute JeuFormWrapper createjeu) {
		jeuService.save(createjeu);
		return new ModelAndView("redirect:/moderateur/jeu");
	}

	@GetMapping("jeu/{id}/update")
	public String getUpdateJeu(@PathVariable(name = "id") Long id, Model model) {
		List<Editeur> editeurs = editeurService.getAllEditeurs();
		List<Genre> genres = genreService.getAllGenres();
		List<Classification> classifications = classificationService.getAllClassifications();
		List<Plateforme> plateformes = plateformeService.getAllPlateformes();
		List<ModeleEconomique> modeleEconomiques = modeleEconomiqueService.getAllModeleEconomiques();

		Jeu jeuToUpdate = jeuService.getJeuByID(id);
		JeuFormWrapper wrapper = new JeuFormWrapper();
		wrapper.setId(id);
		wrapper.setClassification_id(jeuToUpdate.getClassification().getId());
		wrapper.setDateDeSortie(jeuToUpdate.getDateDeSortie());
		wrapper.setEditeur_id(jeuToUpdate.getEditeur().getId());
		wrapper.setGenre_id(jeuToUpdate.getGenre().getId());
		wrapper.setJeu_description(jeuToUpdate.getDescription());
		wrapper.setJeu_nom(jeuToUpdate.getNom());
		wrapper.setModeleEconomique_id(jeuToUpdate.getModeleEconomique().getId());
		List<Long> plateformesIds = new ArrayList<Long>();

		jeuToUpdate.getPlateformes().forEach(plateforme -> {
			plateformesIds.add(plateforme.getId());
		});

		wrapper.setPlateformes_id(plateformesIds);

		model.addAttribute("editeurs", editeurs);
		model.addAttribute("genres", genres);
		model.addAttribute("classifications", classifications);
		model.addAttribute("plateformes", plateformes);
		model.addAttribute("modeleEconomiques", modeleEconomiques);
		model.addAttribute("updateJeu", wrapper);
		return "moderateur/updateJeu";
	}

	@PostMapping("jeu/{id}/update")
	public ModelAndView postUpdateJeu(@PathVariable(name = "id") Long id,
			@ModelAttribute JeuFormWrapper updatedJeu) {
		Jeu jeu = jeuService.getJeuByID(id);

		Classification classification = classificationService.findClassificationById(updatedJeu.getClassification_id());
		jeu.setClassification(classification);

		ModeleEconomique modeleEconomique = modeleEconomiqueService
				.findModeleEconomiqueById(updatedJeu.getModeleEconomique_id());
		jeu.setModeleEconomique(modeleEconomique);

		jeu.setDateDeSortie(updatedJeu.getDateDeSortie());

		jeu.setDescription(updatedJeu.getJeu_description());

		Editeur editeur = editeurService.findEditeurById(updatedJeu.getEditeur_id());
		jeu.setEditeur(editeur);

		Genre genre = genreService.findGenreById(updatedJeu.getGenre_id());
		jeu.setGenre(genre);

		jeu.setNom(updatedJeu.getJeu_nom());

		List<Plateforme> plateformes = new ArrayList<Plateforme>();

		updatedJeu.getPlateformes_id().forEach(plateforme_id -> {
			Plateforme plateforme = plateformeService.findPlateformeById(plateforme_id);
			plateformes.add(plateforme);
		});

		jeu.setPlateformes(plateformes);

		jeuService.save(jeu);
		return new ModelAndView("redirect:/moderateur/jeu");
	}

	@GetMapping("jeu/{id}/uploadimage")
	public String uploadImageJeu(@PathVariable Long id, Model model) {
		Jeu jeu = jeuService.getJeuByID(id);
		model.addAttribute("jeu", jeu);
		return "moderateur/uploadimage";
	}

	@PostMapping("jeu/{id}/uploadimage")
	public ModelAndView uploadImageJeu(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
			@PathVariable Long id) {
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload");
			return new ModelAndView("redirect:/moderateur/jeu");
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			attributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
			Jeu jeu = jeuService.getJeuByID(id);
			jeu.setImage(UPLOADED_DIR + file.getOriginalFilename());
			jeuService.save(jeu);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/moderateur/jeu");
	}

}
