package com.humanbooster.groupe2_cap_entreprise.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="genre_id")
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	@OneToMany(mappedBy = "genre", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
	private List<Jeu> jeux;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Jeu> getJeux() {
		return jeux;
	}

	public void setJeux(List<Jeu> jeux) {
		this.jeux = jeux;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + ", jeux=" + jeux + "]";
	}

	public Genre(String nom, List<Jeu> jeux) {
		super();
		this.nom = nom;
		this.jeux = jeux;
	}

	public Genre() {
		super();
	}

	
	
}
