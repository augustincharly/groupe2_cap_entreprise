package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.repository.JeuRepository;

@Component
public class JeuService implements IJeuService {

	@Autowired
	private JeuRepository jeuRepository;
	
	@Override
	public void save(Jeu jeu) {
		jeuRepository.save(jeu);
	}

}
