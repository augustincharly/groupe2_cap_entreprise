package com.humanbooster.groupe2_cap_entreprise.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;

import com.humanbooster.groupe2_cap_entreprise.repository.AvisRepository;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;

@Service
public class AvisService implements IAvisService{
	
	@Autowired
	private AvisRepository avisRepository;
	
	@Autowired
	private JoueurService joueurService;

	public List<Avis> getAvis() {
		return avisRepository.findAll();
	}

	public List<AvisDTO> getAvisDTOs() {
		List<AvisDTO> avisDTOs = new ArrayList<AvisDTO>();

		avisRepository.findAll().forEach(avis -> {
			AvisDTO avisDTO = TransformerFactory.getAvisTransformer().transform(avis);
			avisDTOs.add(avisDTO);
		});

		return avisDTOs;
	}

	@Override
	public void saveNewAvis(String avis_description, float avis_note, Jeu jeu) {
		Avis avis = new Avis();
		avis.setDescription(avis_description);
		avis.setNote(avis_note);
		avis.setJeu(jeu);
		avis.setDateEnvoi(LocalDateTime.now());
		avis.setJoueur(joueurService.getJoueurById(2));		
		avisRepository.save(avis);
		
	}

	public AvisDTO getAvisDTO(Long id) {
		Avis avisEntity = avisRepository.findById(id).get();
		return TransformerFactory.getAvisTransformer().transform(avisEntity, true);

	}

}
