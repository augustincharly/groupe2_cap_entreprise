package com.humanbooster.groupe2_cap_entreprise.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Classification;
import com.humanbooster.groupe2_cap_entreprise.entity.Editeur;
import com.humanbooster.groupe2_cap_entreprise.entity.Genre;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.entity.Joueur;
import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;
import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;
import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;
import com.humanbooster.groupe2_cap_entreprise.service.IAvisService;
import com.humanbooster.groupe2_cap_entreprise.service.IClassificationService;
import com.humanbooster.groupe2_cap_entreprise.service.IEditeurService;
import com.humanbooster.groupe2_cap_entreprise.service.IGenreService;
import com.humanbooster.groupe2_cap_entreprise.service.IJeuService;
import com.humanbooster.groupe2_cap_entreprise.service.IJoueurService;
import com.humanbooster.groupe2_cap_entreprise.service.IModeleEconomiqueService;
import com.humanbooster.groupe2_cap_entreprise.service.IModerateurService;
import com.humanbooster.groupe2_cap_entreprise.service.IPlateformeService;

//Initialisation de la base de données
@Controller
@RequestMapping(value = "init")
public class InitController {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private IEditeurService editeurService;

	@Autowired
	private IClassificationService classificationService;

	@Autowired
	private IGenreService genreService;

	@Autowired
	private IModeleEconomiqueService modeleEconomiqueService;

	@Autowired
	private IPlateformeService plateformeService;

	@Autowired
	private IJoueurService joueurService;

	@Autowired
	private IModerateurService moderateurService;

	@Autowired
	private IJeuService jeuService;
	
	@Autowired
	private IAvisService avisService;

	@GetMapping
	public ModelAndView getHomePage() {

		Classification classification1 = new Classification();
		classification1.setNom("PEGI 3");
		classificationService.save(classification1);
		Classification classification2 = new Classification();
		classification2.setNom("PEGI 7");
		classificationService.save(classification2);
		Classification classification3 = new Classification();
		classification3.setNom("PEGI 12");
		classificationService.save(classification3);
		Classification classification4 = new Classification();
		classification4.setNom("PEGI 16");
		classificationService.save(classification4);
		Classification classification5 = new Classification();
		classification5.setNom("PEGI 18");
		classificationService.save(classification5);

		Editeur editeur1 = new Editeur();
		editeur1.setNom("UBISOFT");
		editeurService.save(editeur1);
		Editeur editeur2 = new Editeur();
		editeur2.setNom("Nintendo");
		editeurService.save(editeur2);
		Editeur editeur3 = new Editeur();
		editeur3.setNom("FromSoftware");
		editeurService.save(editeur3);
		Editeur editeur4 = new Editeur();
		editeur4.setNom("Electronics Arts");
		editeurService.save(editeur4);
		Editeur editeur5 = new Editeur();
		editeur5.setNom("Activision-Blizzard");
		editeurService.save(editeur5);
		Editeur editeur6 = new Editeur();
		editeur6.setNom("Bandai-Namco");
		editeurService.save(editeur6);
		Editeur editeur7 = new Editeur();
		editeur7.setNom("Take two");
		editeurService.save(editeur7);

		Genre genre1 = new Genre();
		genre1.setNom("action");
		genreService.save(genre1);
		Genre genre2 = new Genre();
		genre2.setNom("aventure");
		genreService.save(genre2);
		Genre genre3 = new Genre();
		genre3.setNom("action-aventure");
		genreService.save(genre3);
		Genre genre4 = new Genre();
		genre4.setNom("jeu de rôle");
		genreService.save(genre4);
		Genre genre5 = new Genre();
		genre5.setNom("réflexion");
		genreService.save(genre5);
		Genre genre6 = new Genre();
		genre6.setNom("simulation");
		genreService.save(genre6);
		Genre genre7 = new Genre();
		genre7.setNom("stratégie");
		genreService.save(genre7);
		Genre genre8 = new Genre();
		genre8.setNom("sports");
		genreService.save(genre8);
		

		ModeleEconomique modeleEconomique1 = new ModeleEconomique();
		modeleEconomique1.setNom("Free To Play");
		modeleEconomiqueService.save(modeleEconomique1);
		ModeleEconomique modeleEconomique2 = new ModeleEconomique();
		modeleEconomique2.setNom("Pay To Play");
		modeleEconomiqueService.save(modeleEconomique2);
		ModeleEconomique modeleEconomique3 = new ModeleEconomique();
		modeleEconomique3.setNom("Free To Start");
		modeleEconomiqueService.save(modeleEconomique3);
		ModeleEconomique modeleEconomique4 = new ModeleEconomique();
		modeleEconomique4.setNom("Play To Earn");
		modeleEconomiqueService.save(modeleEconomique4);

		Plateforme plateforme1 = new Plateforme();
		plateforme1.setNom("PC");
		plateformeService.save(plateforme1);
		Plateforme plateforme2 = new Plateforme();
		plateforme2.setNom("PlayStation 4");
		plateformeService.save(plateforme2);
		Plateforme plateforme3 = new Plateforme();
		plateforme3.setNom("Playstation 5");
		plateformeService.save(plateforme3);
		Plateforme plateforme4 = new Plateforme();
		plateforme4.setNom("Xbox One/One x");
		plateformeService.save(plateforme4);
		Plateforme plateforme5 = new Plateforme();
		plateforme5.setNom("Xbox series X/S");
		plateformeService.save(plateforme5);
		Plateforme plateforme6 = new Plateforme();
		plateforme6.setNom("Nintendo Switch");
		plateformeService.save(plateforme6);
		

		Joueur joueur1 = new Joueur("augustin", passwordEncoder().encode("augustin"), "aa@gmail.com");
		joueur1.setDateDeNaissance(LocalDate.parse("2022-12-21"));
		joueurService.save(joueur1);
		Joueur joueur2 = new Joueur("antoine", passwordEncoder().encode("antoine"), "bb@gmail.com");
		joueur2.setDateDeNaissance(LocalDate.parse("2022-12-21"));
		joueurService.save(joueur2);
		Joueur joueur3 = new Joueur("manfred", passwordEncoder().encode("manfred"), "cc@gmail.com");
		joueur3.setDateDeNaissance(LocalDate.parse("2022-12-21"));
		joueurService.save(joueur3);

		Moderateur moderateur = new Moderateur("modo", passwordEncoder().encode("MODO"), "modo@gmail.com");

		moderateurService.save(moderateur);

		Jeu jeu1 = new Jeu();
		jeu1.setNom("Elden Ring");
		jeu1.setDescription("...");
		jeu1.setImage("https://image.api.playstation.com/vulcan/ap/rnd/202107/1612/Y5RHNmzAtc6sRYwZlYiKHAxN.png");
		jeu1.addPlateformes(plateforme1);
		jeu1.addPlateformes(plateforme3);
		jeu1.addPlateformes(plateforme5);
		jeu1.setDateDeSortie(LocalDate.now().minusMonths(1));
		modeleEconomique2.addJeu(jeu1);
		editeur3.addJeu(jeu1);
		genre3.addJeu(jeu1);
		classification5.addJeu(jeu1);
		jeuService.save(jeu1);

		Jeu jeu2 = new Jeu();
		jeu2.setNom("Pacman");
		jeu2.setDescription("...");
		jeu2.setImage(
				"https://fs-prod-cdn.nintendo-europe.com/media/images/10_share_images/games_15/nes_5/H2x1_NES_PacMan_image1600w.jpg");
		jeu2.addPlateformes(plateforme1);
		jeu2.addPlateformes(plateforme6);
		jeu2.setDateDeSortie(LocalDate.now().minusMonths(1));
		modeleEconomique2.addJeu(jeu2);
		editeur2.addJeu(jeu2);
		genre2.addJeu(jeu2);
		classification1.addJeu(jeu2);
		jeuService.save(jeu2);
		
		Avis avis1=new Avis();
		avis1.setDateEnvoi(LocalDateTime.now());
		avis1.setJeu(jeu1);
		avis1.setDescription("un jeu de bourrin");
		avis1.setNote((float)16.5);
		avis1.setJoueur(joueur1);
		avisService.saveNewAvis(avis1);
		
		Avis avis2=new Avis();
		avis2.setDateEnvoi(LocalDateTime.now());
		avis2.setJeu(jeu2);
		avis2.setDescription("un jeu d'arcade");
		avis2.setNote((float)12.0);
		avis2.setJoueur(joueur3);
		avisService.saveNewAvis(avis2);
		
		Avis avis3=new Avis();
		avis3.setDateEnvoi(LocalDateTime.now());
		avis3.setJeu(jeu2);
		avis3.setDescription("un deuxième avis");
		avis3.setNote((float)6.0);
		avis3.setJoueur(joueur2);
		avisService.saveNewAvis(avis3);
		
		Avis avis4=new Avis();
		avis4.setDateEnvoi(LocalDateTime.now());
		avis4.setJeu(jeu2);
		avis4.setDescription("un troisième avis");
		avis4.setNote((float)15.0);
		avis4.setJoueur(joueur1);
		avisService.saveNewAvis(avis4);
		
		Avis avis5=new Avis();
		avis5.setDateEnvoi(LocalDateTime.now());
		avis5.setJeu(jeu1);
		avis5.setDescription("un premier avis");
		avis5.setNote((float)17.0);
		avis5.setJoueur(joueur2);
		avisService.saveNewAvis(avis5);
		
		Avis avis6=new Avis();
		avis6.setDateEnvoi(LocalDateTime.now());
		avis6.setJeu(jeu1);
		avis6.setDescription("un deuxième avis");
		avis6.setNote((float)12.0);
		avis6.setJoueur(joueur3);
		avisService.saveNewAvis(avis6);
		
		
		

		return new ModelAndView("redirect:/login");
	}

}
