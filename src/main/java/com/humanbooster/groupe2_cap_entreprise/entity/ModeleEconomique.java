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
@Table(name = "modeleEconomique")
public class ModeleEconomique {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="modeleEconomique_id")
	private Long id;
	
	@Column(name = "nom")
	private String nom;
	
	
	@OneToMany(mappedBy = "modeleEconomique", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
	private List<Jeu> jeux;
	
}
