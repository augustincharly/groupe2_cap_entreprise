package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Joueur;
import com.humanbooster.groupe2_cap_entreprise.repository.JoueurRepository;

@Component
public class JoueurService implements IJoueurService{

	@Autowired
	private JoueurRepository joueurRepository;
	
	@Override
	public void save(Joueur joueur) {
		joueurRepository.save(joueur);
	}

	public Joueur getJoueurById(long id) {
		return joueurRepository.findById(id).get();
	}

}
