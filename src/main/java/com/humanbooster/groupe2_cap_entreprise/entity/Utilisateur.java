package com.humanbooster.groupe2_cap_entreprise.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;



@Entity
@Table(name="utilisateur")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="utilisateur_id")
	protected Long id;
	
	@Column(name="pseudo", nullable = false)
	protected String pseudo;
	
	@Column(name="motDePasse", nullable = false)
	protected String motDePasse;
	
	@Column(name="email", nullable = false)
	protected String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Utilisateur(String pseudo, String motDePasse, String email) {
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
	}

	public Utilisateur() {
		super();
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", pseudo=" + pseudo + ", motDePasse=" + motDePasse + ", email=" + email + "]";
	}


}
	
	
	
	
	

