package com.humanbooster.groupe2_cap_entreprise.dto;

public class JeuDTO {

	private Long id;
	private String image;
	private String nom;
	private String editeur;

	public JeuDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "JeuDTO [id=" + id + ", image=" + image + ", nom=" + nom + ", editeur=" + editeur + "]";
	}

}
