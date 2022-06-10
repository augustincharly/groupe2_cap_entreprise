package com.humanbooster.groupe2_cap_entreprise.transformer;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.dto.JeuDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;

@Component
public class JeuTransformer {

	public JeuDTO transform(Jeu jeu) {
		JeuDTO jeuDTO = new JeuDTO();

		jeuDTO.setId(jeu.getId());
		jeuDTO.setImage(jeu.getImage());
		jeuDTO.setEditeur(jeu.getEditeur().getNom());
		jeuDTO.setNom(jeu.getNom());

		return jeuDTO;
	}

}
