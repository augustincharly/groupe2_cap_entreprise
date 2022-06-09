package com.humanbooster.groupe2_cap_entreprise.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

@Entity
@Table(name = "jeu")
public class Jeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="jeu_id")
	private Long id;
	
	@NotEmpty
	@Column(name = "nom",nullable = false)
	private String nom;
	
	@NotEmpty
	@Column(name = "description",nullable = false)
	private String description;
	
	@Past
	@Column(name = "dateDeSortie")
	private LocalDate dateDeSortie;
	
	@Column(name = "image")
	private String image;
	
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(joinColumns = @JoinColumn(name = "jeu_id"), inverseJoinColumns = @JoinColumn(name = "plateforme_id",nullable = false))
	private List<Plateforme> plateformes;
	
	
	@ManyToOne
	@JoinColumn(name = "modeleEconomique_id",nullable = false)
	private ModeleEconomique modeleEconomique;
	
	@ManyToOne
	@JoinColumn(name = "editeur_id",nullable = false)
	private Editeur editeur;
	
	@ManyToOne
	@JoinColumn(name = "genre_id",nullable = false)
	private Genre genre;
	
	@ManyToOne
	@JoinColumn(name = "classification_id",nullable = false)
	private Classification classification;
	
	@OneToMany(mappedBy = "jeu", cascade =  CascadeType.ALL, orphanRemoval = true) 
	private List<Avis> avis;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "moderateur_id")
	private Moderateur moderateur;
	
	public Moderateur getModerateur() {
		return moderateur;
	}

	public void setModerateur(Moderateur moderateur) {
		this.moderateur = moderateur;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDeSortie() {
		return dateDeSortie;
	}

	public void setDateDeSortie(LocalDate dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Plateforme> getPlateformes() {
		return plateformes;
	}

	public void setPlateformes(List<Plateforme> plateformes) {
		this.plateformes = plateformes;
	}

	public ModeleEconomique getModeleEconomique() {
		return modeleEconomique;
	}

	public void setModeleEconomique(ModeleEconomique modeleEconomique) {
		this.modeleEconomique = modeleEconomique;
	}

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public List<Avis> getAvis() {
		return avis;
	}

	public void setAvis(List<Avis> avis) {
		this.avis = avis;
	}



	@Override
	public String toString() {
		return "Jeu [id=" + id + ", nom=" + nom + ", description=" + description + ", dateDeSortie=" + dateDeSortie
				+ ", image=" + image + ", plateformes=" + plateformes + ", modeleEconomique=" + modeleEconomique
				+ ", editeur=" + editeur + ", genre=" + genre + ", classification=" + classification + ", avis=" + avis
				+ ", moderateur=" + moderateur + "]";
	}

	public Jeu() {
		super();
	}

	public Jeu(String nom, String description, LocalDate dateDeSortie, String image, List<Plateforme> plateformes,
			ModeleEconomique modeleEconomique, Editeur editeur, Genre genre, Classification classification,
			List<Avis> avis, Moderateur moderateur) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDeSortie = dateDeSortie;
		this.image = image;
		this.plateformes = plateformes;
		this.modeleEconomique = modeleEconomique;
		this.editeur = editeur;
		this.genre = genre;
		this.classification = classification;
		this.avis = avis;
		this.moderateur = moderateur;
	}

	public void addPlateformes(Plateforme plateforme) {
		plateformes.add(plateforme);
		plateforme.getJeux().add(this);
		
	}
	
	public void removePlateformes(Plateforme plateforme) {
		plateformes.remove(plateforme);
		plateforme.getJeux().remove(this);
		
	}
	
	
	
}
