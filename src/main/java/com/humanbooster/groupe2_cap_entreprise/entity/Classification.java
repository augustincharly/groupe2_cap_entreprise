package com.humanbooster.groupe2_cap_entreprise.entity;

import java.util.ArrayList;
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
@Table(name = "classification")
public class Classification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="classification_id")
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	@OneToMany(mappedBy = "genre", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
	private List<Jeu> jeux = new ArrayList<Jeu>();

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
		return "Classification [id=" + id + ", nom=" + nom + ", jeux=" + jeux + "]";
	}

	public Classification(String nom, List<Jeu> jeux) {
		super();
		this.nom = nom;
		this.jeux = jeux;
	}

	public Classification() {
		super();
	}
	
	public void addJeu(Jeu jeu) {
		jeux.add(jeu);
		jeu.setClassification(this);
		}
	
	public void removeJeu(Jeu jeu) {
		jeux.remove(jeu);
		jeu.setClassification(null);
	}
}
