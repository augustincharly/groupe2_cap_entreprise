package com.humanbooster.groupe2_cap_entreprise.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class JeuDTO {

	private Long id;
	private String image;
	private String nom;
	private String editeur;
	private LocalDate dateDeSortie;
	private String description;
	private String genre;
	private String classification;
	private ArrayList<String> plateformes;
	private String modeleEconomique;

	public JeuDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public LocalDate getDateDeSortie() {
		return dateDeSortie;
	}

	public void setDateDeSortie(LocalDate dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public ArrayList<String> getPlateformes() {
		return plateformes;
	}

	public void setPlateformes(ArrayList<String> plateformes) {
		this.plateformes = plateformes;
	}

	public String getModeleEconomique() {
		return modeleEconomique;
	}

	public void setModeleEconomique(String modeleEconomique) {
		this.modeleEconomique = modeleEconomique;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEditeur() {
		return editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	@Override
	public String toString() {
		return "JeuDTO [id=" + id + ", image=" + image + ", nom=" + nom + ", editeur=" + editeur + ", dateDeSortie="
				+ dateDeSortie + ", description=" + description + ", genre=" + genre + ", classification="
				+ classification + ", plateformes=" + plateformes + ", modeleEconomique=" + modeleEconomique + "]";
	}

}
