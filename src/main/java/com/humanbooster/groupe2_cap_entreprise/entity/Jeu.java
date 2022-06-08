package com.humanbooster.groupe2_cap_entreprise.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Jeu {


	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(joinColumns = @JoinColumn(name = "jeu_id"), inverseJoinColumns = @JoinColumn(name = "plateforme_id"))
	private List<Plateforme> plateformes;
	
	
	@ManyToOne
	@JoinColumn(name = "modeleEconomique_id")
	private ModeleEconomique modeleEconomique;
}
