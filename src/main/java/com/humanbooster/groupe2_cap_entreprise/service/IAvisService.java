package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;

@Service
public interface IAvisService {
	
	public List<Avis> getAvis();

	public List<AvisDTO> getAvisDTOs();

	public void saveNewAvis(String avis_description, float avis_note, Jeu jeu);

}
