package com.humanbooster.groupe2_cap_entreprise.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AvisDTO {

	private Long id;
	private LocalDateTime dateEnvoi;
	private String nomJeu;
	private String pseudoJoueur;
	private String description;
	private float note;
	private String image;
	private String nomModerateur;
	private ArrayList<String> plateformes;

	public AvisDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getPlateformes() {
		return plateformes;
	}

	public void setPlateformes(ArrayList<String> plateformes) {
		this.plateformes = plateformes;
	}

	public LocalDateTime getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(LocalDateTime dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public String getNomJeu() {
		return nomJeu;
	}

	public void setNomJeu(String nomJeu) {
		this.nomJeu = nomJeu;
	}

	public String getPseudoJoueur() {
		return pseudoJoueur;
	}

	public void setPseudoJoueur(String pseudoJoueur) {
		this.pseudoJoueur = pseudoJoueur;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNomModerateur() {
		return nomModerateur;
	}

	public void setNomModerateur(String nomModerateur) {
		this.nomModerateur = nomModerateur;
	}

	@Override
	public String toString() {
		return "AvisDTO [dateEnvoi=" + dateEnvoi + ", nomJeu=" + nomJeu + ", pseudoJoueur=" + pseudoJoueur + ", note="
				+ note + ", image=" + image + ", nomModerateur=" + nomModerateur + "]";
	}

}
