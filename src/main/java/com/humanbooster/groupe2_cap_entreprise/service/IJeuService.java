package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;

import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.formwrapper.JeuFormWrapper;

@Component
public interface IJeuService {

	public List<Jeu> getAllJeux();
	
	public List<JeuDTO> getAllJeuDTOs();

	public Jeu getJeuByID(Long jeu_id);

	public JeuDTO getJeuDTO(Long id);

	public void save(Jeu jeu);

	public void delete(Long id);

	public List<JeuDTO> getJeux();

	public void save(JeuFormWrapper createjeu);

	public List<JeuDTO> getJeuDTOsWithPagination(Pageable pagination);

	public Page<Jeu> getJeuPageDTOsWithPagination(Pageable pagination);

	public Page<Jeu> getAllPageJeuxSorted(Integer pageNum, String sortField, String sortDir);

}
