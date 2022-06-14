package com.humanbooster.groupe2_cap_entreprise.formwrapper;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


public class CreateJeuFormWrapper {
	
	private String jeu_nom;
	private Long editeur_id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeSortie;
	private String jeu_description;
	private Long genre_id;
	private Long classification_id;
	private List<Long> plateformes_id;
	private Long modeleEconomique_id;
	public String getJeu_nom() {
		return jeu_nom;
	}
	public void setJeu_nom(String jeu_nom) {
		this.jeu_nom = jeu_nom;
	}
	public Long getEditeur_id() {
		return editeur_id;
	}
	public void setEditeur_id(Long editeur_id) {
		this.editeur_id = editeur_id;
	}
	public LocalDate getDateDeSortie() {
		return dateDeSortie;
	}
	public void setDateDeSortie(LocalDate dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}
	public String getJeu_description() {
		return jeu_description;
	}
	public void setJeu_description(String jeu_description) {
		this.jeu_description = jeu_description;
	}
	public Long getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
	}
	public Long getClassification_id() {
		return classification_id;
	}
	public void setClassification_id(Long classification_id) {
		this.classification_id = classification_id;
	}

	public List<Long> getPlateformes_id() {
		return plateformes_id;
	}
	public void setPlateformes_id(List<Long> plateformes_id) {
		this.plateformes_id = plateformes_id;
	}
	public Long getModeleEconomique_id() {
		return modeleEconomique_id;
	}
	public void setModeleEconomique_id(Long modeleEconomique_id) {
		this.modeleEconomique_id = modeleEconomique_id;
	}
	
	

}
