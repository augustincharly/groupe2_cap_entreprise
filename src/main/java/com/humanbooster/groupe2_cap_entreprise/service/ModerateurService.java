package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;
import com.humanbooster.groupe2_cap_entreprise.repository.ModerateurRepository;

@Component
public class ModerateurService implements IModerateurService {

	@Autowired
	private ModerateurRepository moderateurRepository;

	@Override
	public void save(Moderateur moderateur) {
		moderateurRepository.save(moderateur);
	}

	@Override
	public Moderateur getModerateur(Long id) {
		return moderateurRepository.findById(id).get();
	}

}
