package com.humanbooster.groupe2_cap_entreprise.transformer;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;

@Component
public class AvisTransformer {

	public AvisDTO transform(Avis avis) {
		AvisDTO avisDTO = new AvisDTO();
		avisDTO.setDateEnvoi(avis.getDateEnvoi());
		avisDTO.setImage(avis.getJeu().getImage());
		avisDTO.setNomJeu(avis.getJeu().getNom());
		if (avis.getModerateur() != null) {
			avisDTO.setNomModerateur(avis.getModerateur().getPseudo());
		}
		avisDTO.setNote(avis.getNote());
		avisDTO.setPseudoJoueur(avis.getJoueur().getPseudo());

		return avisDTO;
	}

}
