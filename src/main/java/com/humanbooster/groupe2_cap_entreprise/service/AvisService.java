package com.humanbooster.groupe2_cap_entreprise.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;
import com.humanbooster.groupe2_cap_entreprise.repository.AvisRepository;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;

@Service
public class AvisService implements IAvisService {

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
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String pseudo = authentication.getName().toString();
		avis.setJoueur(joueurService.getJoueurByPseudo(pseudo));
		avisRepository.save(avis);

	}

	public AvisDTO getAvisDTO(Long id) {
		Avis avisEntity = avisRepository.findById(id).get();
		return TransformerFactory.getAvisTransformer().transform(avisEntity, true);

	}

	@Override
	public void saveNewAvis(Avis avis) {
		avisRepository.save(avis);

	}

	@Override
	public void validateAvis(Avis avis, Moderateur moderateur) {
		avis.setDateModeration(LocalDateTime.now());
		avis.setModerateur(moderateur);
		avisRepository.save(avis);
	}

	@Override
	public void delete(Long id) {
		avisRepository.deleteById(id);
	}

	@Override
	public Avis getAvis(Long id) {
		return avisRepository.findById(id).get();
	}
	
	public List<AvisDTO> getAvisDTOsWithPagination(String pseudo, Pageable pagination){
		List<Avis> avis =  avisRepository.findAll(pseudo, pagination).getContent();
		List<AvisDTO> avisDTOs = new ArrayList<>();
		for(Avis aviToAdd : avis) {
			avisDTOs.add(TransformerFactory.getAvisTransformer().transform(aviToAdd));
		}
				
		return avisDTOs;
	}
	
	public Page<Avis> getAvisPageDTOsWithPagination(String pseudo, Pageable pagination){
		Page<Avis> avisDTOs =  avisRepository.findAll(pseudo, pagination);
		return avisDTOs;
	}

}
