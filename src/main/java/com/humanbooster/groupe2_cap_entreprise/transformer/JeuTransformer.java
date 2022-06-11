package com.humanbooster.groupe2_cap_entreprise.transformer;

import java.util.ArrayList;

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

		ArrayList<String> plateformes = new ArrayList<String>();

		jeu.getPlateformes().forEach(plateforme -> {
			plateformes.add(plateforme.getNom());
		});

		jeuDTO.setPlateformes(plateformes);

		jeuDTO.setClassification(jeu.getClassification().getNom());
		jeuDTO.setDescription(jeu.getDescription());
		jeuDTO.setDateDeSortie(jeu.getDateDeSortie());
		jeuDTO.setGenre(jeu.getGenre().getNom());
		jeuDTO.setModeleEconomique(jeu.getModeleEconomique().getNom());

		return jeuDTO;
	}

}
