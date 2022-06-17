package com.humanbooster.groupe2_cap_entreprise.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.humanbooster.groupe2_cap_entreprise.formwrapper.FilterWrapper;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.JeuFormWrapper;
import com.humanbooster.groupe2_cap_entreprise.service.IClassificationService;
import com.humanbooster.groupe2_cap_entreprise.service.IEditeurService;
import com.humanbooster.groupe2_cap_entreprise.service.IGenreService;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;
import com.humanbooster.groupe2_cap_entreprise.service.IModeleEconomiqueService;
import com.humanbooster.groupe2_cap_entreprise.service.IPlateformeService;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;
import com.humanbooster.groupe2_cap_entreprise.utils.PaginatedList;

@Controller
@RequestMapping("moderateur")
public class ModerateurController {

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

	@RequestMapping("avis/page/{id}")
	public String viewPage(Model model, @PathVariable(name = "id") Integer pageNum,
			@RequestParam(defaultValue = "jeu", name = "sortField") String sortField,
			@RequestParam(defaultValue = "asc", name = "sortDir") String sortDir,
			@RequestParam(defaultValue = "null", name = "moderated") String statut) {

		List<Avis> avisListe = avisService.getAllAvisSorted(sortField, sortDir);

		if (!statut.equals("null")) {
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

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", paginatedList.numberOfPages());
		model.addAttribute("totalItems", paginatedList.getTotalItems());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("statutFilter", statut);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("list_avis", avisDTOsToDisplay);
		model.addAttribute("filterWrapper", new FilterWrapper());

		return "moderateur/avisListe";
	}

	@PostMapping("avis/page/{id}")
	public ModelAndView viewPagePost(Model model, @PathVariable(name = "id") Integer pageNum,
			@ModelAttribute FilterWrapper filterWrapper) {
		Boolean statut = filterWrapper.getModerated();
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/moderateur/avis/page/0?sortField=jeu&sortDir=asc&moderated=" + statut);
		return modelAndView;
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
		return new ModelAndView("redirect:/moderateur/avis/page/0");
	}

	@GetMapping("avis/{id}/validate")
	public ModelAndView validateAvis(@PathVariable(name = "id") Long id) {
		Avis avis = avisService.getAvis(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String pseudo = authentication.getName().toString();
		Moderateur moderateur = moderateurService.getModerateurByPseudo(pseudo);
		avisService.validateAvis(avis, moderateur);
		return new ModelAndView("redirect:/moderateur/avis/page/0");
	}

	@GetMapping("jeu/page/{id}")
	public String getJeux(@PathVariable(name = "id") Integer pageNum, @Param("sortField") String sortField,
			@Param("sortDir") String sortDir, Model model) {
		if (sortField == null) {
			sortField = "nom";
		}
		if (sortDir == null) {
			sortDir = "asc";
		}

		List<JeuDTO> jeuxDTOs = new ArrayList<JeuDTO>();
		Page<Jeu> page = jeuService.getAllPageJeuxSorted(pageNum, sortField, sortDir);
		List<Jeu> jeux = jeuService.getAllPageJeuxSorted(pageNum, sortField, sortDir).getContent();
		for (Jeu jeuToAdd : jeux) {
			jeuxDTOs.add(TransformerFactory.getJeuTransformer().transform(jeuToAdd));
		}

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("list_jeux", jeuxDTOs);

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
		return new ModelAndView("redirect:/moderateur/jeu/page/0");
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
		return new ModelAndView("redirect:/moderateur/jeu/page/0");
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
	public ModelAndView postUpdateJeu(@PathVariable(name = "id") Long id, @ModelAttribute JeuFormWrapper updatedJeu) {
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
		return new ModelAndView("redirect:/moderateur/jeu/page/0");
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
			return new ModelAndView("redirect:/moderateur/jeu/page/0");
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(EnvironmentVariable.PROJECT_DIR + EnvironmentVariable.UPLOADED_IMAGE_DIR + file.getOriginalFilename());
			Files.write(path, bytes);
						attributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
			Jeu jeu = jeuService.getJeuByID(id);
			jeu.setImage(UPLOADED_DIR + file.getOriginalFilename());
			jeuService.save(jeu);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/moderateur/jeu/page/0");
	}

}
