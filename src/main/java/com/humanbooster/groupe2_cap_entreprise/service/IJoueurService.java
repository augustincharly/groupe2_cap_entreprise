package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Joueur;

@Component
public interface IJoueurService {

	void save(Joueur joueur);
	
	public Joueur getJoueurById(long id);

}
