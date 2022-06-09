package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;

@Component
public interface IPlateformeService {

	void save(Plateforme plateforme);

}
