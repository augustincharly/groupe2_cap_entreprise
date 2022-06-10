package com.humanbooster.groupe2_cap_entreprise.transformer;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;

@Component
public class AvisTransformer {

	public AvisDTO transform(Avis avis) {
		return transform(avis, false);
	}

	public AvisDTO transform(Avis avis, boolean withPlateformes) {
		AvisDTO avisDTO = new AvisDTO();

		avisDTO.setId(avis.getId());
		avisDTO.setDateEnvoi(avis.getDateEnvoi());
		avisDTO.setImage(avis.getJeu().getImage());
		avisDTO.setNomJeu(avis.getJeu().getNom());
		if (avis.getModerateur() != null) {
			avisDTO.setNomModerateur(avis.getModerateur().getPseudo());
		}
		avisDTO.setNote(avis.getNote());
		avisDTO.setPseudoJoueur(avis.getJoueur().getPseudo());
		avisDTO.setDescription(avis.getDescription());

		if (withPlateformes) {
			ArrayList<String> plateformes = new ArrayList<>();
			avis.getJeu().getPlateformes().forEach(plateforme -> {
				plateformes.add(plateforme.getNom());
			});
			avisDTO.setPlateformes(plateformes);
		}

		return avisDTO;
	}

}
