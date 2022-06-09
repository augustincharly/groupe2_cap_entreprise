package com.humanbooster.groupe2_cap_entreprise.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "plateforme")
public class Plateforme {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="plateforme_id")
	private long id;
	
	@Column(name = "nom")
	private String nom;
	
	
	@ManyToMany(mappedBy = "plateformes", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Jeu> jeux=new ArrayList<Jeu>();


	public long getId() {
		return id;
	}


	public void setId(long id) {
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
		return "Plateforme [id=" + id + ", nom=" + nom + ", jeux=" + jeux + "]";
	}


	public Plateforme() {
		super();
	}


	public Plateforme(String nom, List<Jeu> jeux) {
		super();
		this.nom = nom;
		this.jeux = jeux;
	}
	
	
	
	
	
}
