package com.humanbooster.groupe2_cap_entreprise.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.humanbooster.groupe2_cap_entreprise.entity.Classification;
import com.humanbooster.groupe2_cap_entreprise.entity.Editeur;
import com.humanbooster.groupe2_cap_entreprise.entity.Genre;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.entity.Joueur;
import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;
import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;
import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;
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
	
	@GetMapping("/init")
	public String getHomePage() {
		
		Classification classification1= new Classification();
		classification1.setNom("PEGI 6");
		classificationService.save(classification1);
		Classification classification2= new Classification();
		classification2.setNom("PEGI 12");
		classificationService.save(classification2);
		Classification classification3= new Classification();
		classification3.setNom("PEGI 16");
		classificationService.save(classification3);
				
		Editeur editeur1 = new Editeur();
		editeur1.setNom("UBISOFT");
		editeurService.save(editeur1);
		Editeur editeur2 = new Editeur();
		editeur2.setNom("EA");
		editeurService.save(editeur2);
		Editeur editeur3 = new Editeur();
		editeur3.setNom("FROMSOFTWARE");
		editeurService.save(editeur3);
		
		Genre genre1 = new Genre();
		genre1.setNom("horreur");
		genreService.save(genre1);
		Genre genre2 = new Genre();
		genre2.setNom("course");
		genreService.save(genre2);
		Genre genre3 = new Genre();
		genre3.setNom("FPS");
		genreService.save(genre3);
		
		ModeleEconomique modeleEconomique1=new ModeleEconomique();
		modeleEconomique1.setNom("Free To Play");
		modeleEconomiqueService.save(modeleEconomique1);
		ModeleEconomique modeleEconomique2=new ModeleEconomique();
		modeleEconomique2.setNom("Pay To Play");
		modeleEconomiqueService.save(modeleEconomique2);
		
		Plateforme plateforme1=new Plateforme();
		plateforme1.setNom("PC");
		plateformeService.save(plateforme1);
		Plateforme plateforme2=new Plateforme();
		plateforme2.setNom("PlayStation 5");
		plateformeService.save(plateforme2);
		Plateforme plateforme3=new Plateforme();
		plateforme3.setNom("Xbox series X/S");
		plateformeService.save(plateforme3);
		
		Joueur joueur1 = new Joueur("augustin",passwordEncoder().encode("augustin"),"aa@gmail.com");
		joueur1.setDateDeNaissance(LocalDate.parse("2022-12-21"));
		joueurService.save(joueur1);
		Joueur joueur2 = new Joueur("antoine",passwordEncoder().encode("antoine"),"bb@gmail.com");
		joueur2.setDateDeNaissance(LocalDate.parse("2022-12-21"));
		joueurService.save(joueur2);
		Joueur joueur3 = new Joueur("manfred",passwordEncoder().encode("manfred"),"cc@gmail.com");
		joueur3.setDateDeNaissance(LocalDate.parse("2022-12-21"));
		joueurService.save(joueur3);
		
		Moderateur moderateur = new Moderateur("modo", passwordEncoder().encode("MODO"), "modo@gmail.com");
	
		moderateurService.save(moderateur);
		
		Jeu jeu1=new Jeu();
		jeu1.setNom("Elden Ring");
		jeu1.setDescription("...");
		modeleEconomique1.addJeu(jeu1);
		editeur1.addJeu(jeu1);
		genre1.addJeu(jeu1);
		classification1.addJeu(jeu1);
		jeuService.save(jeu1);
		
		Jeu jeu2=new Jeu();
		jeu2.setNom("PacMan");
		jeu2.setDescription("...");
		modeleEconomique2.addJeu(jeu2);
		editeur2.addJeu(jeu2);
		genre2.addJeu(jeu2);
		classification2.addJeu(jeu2);
		jeuService.save(jeu2);

		
		
		return "home";
	}


	
}
