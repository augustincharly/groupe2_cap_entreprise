package com.humanbooster.groupe2_cap_entreprise.dto;

import java.time.LocalDateTime;

public class AvisDTO {

	private LocalDateTime dateEnvoi;
	private String nomJeu;
	private String pseudoJoueur;
	private float note;
	private String image;
	private String nomModerateur;

	public AvisDTO() {
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
