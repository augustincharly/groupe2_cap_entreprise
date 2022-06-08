package com.humanbooster.groupe2_cap_entreprise.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Avis {

	
	@ManyToOne
	@JoinColumn(name = "joueur_id")
	private Joueur joueur;
}
