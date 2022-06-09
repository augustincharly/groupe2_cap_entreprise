package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Moderateur;

@Component
public interface IModerateurService {

	void save(Moderateur moderateur);

}
