package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;

@Component
public interface IAvisService {

	public List<Avis> getAvis();

	public List<AvisDTO> getAvisDTOs();

	public AvisDTO getAvisDTO(Long id);

	public Avis getAvis(Long id);

	public void saveNewAvis(String avis_description, float avis_note, Jeu jeu);

	public void saveNewAvis(Avis avis);

	public void validateAvis(Avis avis, Moderateur moderateur);

	public void delete(Long id);

	Page<Avis> getAllPageAvisSorted(String pseudo, int pageNum, String sortField, String sortDir);

}
