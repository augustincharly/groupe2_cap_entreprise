package com.humanbooster.groupe2_cap_entreprise.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Classification;
import com.humanbooster.groupe2_cap_entreprise.entity.Editeur;
import com.humanbooster.groupe2_cap_entreprise.entity.Genre;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;
import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.CreateJeuFormWrapper;
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

	private final String UPLOADED_FOLDER = "C:\\Users\\Sam\\AppData\\Local\\GitHubDesktop\\app-3.0.0\\groupe2_cap_entreprise\\src\\main\\resources\\static\\";
	private final String UPLOADED_DIR = "/";
	
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
	private IAvisService avisService;

	@GetMapping("avis")
	public String getAvis(Model model) {
		List<AvisDTO> avisDTOs = avisService.getAvisDTOs();

		model.addAttribute("list_avis", avisDTOs);

		return "moderateur/avisListe";
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
	
	@GetMapping("jeu/{id}/uploadimage")
	public String uploadImageJeu(@PathVariable Long id, Model model) {
		Jeu jeu = jeuService.getJeuByID(id);
		model.addAttribute("jeu", jeu);
		return "moderateur/uploadimage";
	}
	
	@PostMapping("jeu/{id}/uploadimage")
	public ModelAndView uploadImageJeu(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,@PathVariable Long id) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload");
            return new ModelAndView("redirect:/moderateur/jeu");
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            attributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            	Jeu jeu=jeuService.getJeuByID(id);
            	jeu.setImage(UPLOADED_DIR + file.getOriginalFilename());
            	jeuService.save(jeu);
            

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/moderateur/jeu");
    }
		
	}
	

