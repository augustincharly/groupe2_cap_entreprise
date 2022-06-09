package com.humanbooster.groupe2_cap_entreprise.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "avis")
public class Avis {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="avis_id")
	private Long id;
	
	@NotEmpty
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "dateEnvoi")
	private LocalDateTime dateEnvoi;
	
	@Min(value = 0)
	@Max(value = 20)
	@Column(name = "note")
	private float note;
	
	@Column(name = "dateModeration")
	private LocalDateTime dateModeration;
	
	@ManyToOne
	@JoinColumn(name = "joueur_id",nullable = false)
	private Joueur joueur;
	
	@ManyToOne
	@JoinColumn(name = "jeu_id", nullable = false)
	private Jeu jeu;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "moderateur_id")
	private Moderateur moderateur;

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

	public LocalDateTime getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(LocalDateTime dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public LocalDateTime getDateModeration() {
		return dateModeration;
	}

	public void setDateModeration(LocalDateTime dateModeration) {
		this.dateModeration = dateModeration;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Moderateur getModerateur() {
		return moderateur;
	}

	public void setModerateur(Moderateur moderateur) {
		this.moderateur = moderateur;
	}

	@Override
	public String toString() {
		return "Avis [id=" + id + ", description=" + description + ", dateEnvoi=" + dateEnvoi + ", note=" + note
				+ ", dateModeration=" + dateModeration + ", joueur=" + joueur + ", jeu=" + jeu + ", moderateur="
				+ moderateur + "]";
	}

	public Avis() {
		super();
	}

	public Avis(String description, LocalDateTime dateEnvoi, float note, LocalDateTime dateModeration, Joueur joueur,
			Jeu jeu, Moderateur moderateur) {
		super();
		this.description = description;
		this.dateEnvoi = dateEnvoi;
		this.note = note;
		this.dateModeration = dateModeration;
		this.joueur = joueur;
		this.jeu = jeu;
		this.moderateur = moderateur;
	}
	
	
	
	
}
