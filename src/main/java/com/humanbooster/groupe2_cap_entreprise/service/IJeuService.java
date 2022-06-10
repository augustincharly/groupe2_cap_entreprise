package com.humanbooster.groupe2_cap_entreprise.service;



import java.util.List;

import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;

public interface IJeuService {

	void save(Jeu jeu);
	
	public List<Jeu> getAllJeux();

	public Jeu getJeuByID(Long jeu_id);

}
