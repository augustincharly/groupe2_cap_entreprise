package com.humanbooster.groupe2_cap_entreprise.service;



import java.util.List;

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

	public List<Jeu> getAllJeux() {
		try {
			return jeuRepository.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Jeu getJeuByID(Long jeu_id) {
		Jeu jeu = jeuRepository.findById(jeu_id).get();
		return jeu;
	}



}
