package com.humanbooster.groupe2_cap_entreprise.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "joueur")
public class Joueur extends Utilisateur {

	@Column(name = "dateDeNaissance", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateDeNaissance;

	@OneToMany(mappedBy = "joueur", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Avis> avis=new ArrayList<>();

	public Joueur() {
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public List<Avis> getAvis() {
		return avis;
	}

	public void setAvis(List<Avis> avis) {
		this.avis = avis;
	}

	public Joueur(String pseudo, String motDePasse, String email) {
		super(pseudo, motDePasse, email);
	}

	public Joueur(String pseudo, String motDePasse, String email, LocalDate dateDeNaissance, List<Avis> avis) {
		super(pseudo, motDePasse, email);
		this.dateDeNaissance = dateDeNaissance;
		this.avis = avis;
	}

	@Override
	public String toString() {
		return "Joueur [dateDeNaissance=" + dateDeNaissance + ", avis=" + avis + "]";
	}

	public void addAvis(Avis avis) {
		this.avis.add(avis);
		avis.setJoueur(this);

	}

	public void removeAvis(Avis avis) {
		this.avis.remove(avis);
		avis.setJoueur(null);
	}

}
