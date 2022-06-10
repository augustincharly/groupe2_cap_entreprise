package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;
import com.humanbooster.groupe2_cap_entreprise.repository.JeuRepository;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;

@Component
public class JeuService implements IJeuService {

	@Autowired
	private JeuRepository jeuRepository;

	public List<JeuDTO> getJeux() {
		List<JeuDTO> jeuDTOs = new ArrayList<JeuDTO>();

		jeuRepository.findAll().forEach(jeu -> {
			JeuDTO jeuDTO = TransformerFactory.getJeuTransformer().transform(jeu);
			jeuDTOs.add(jeuDTO);
		});

		return jeuDTOs;
	}

	@Override
	public void save(Jeu jeu) {
		jeuRepository.save(jeu);
	}

}
