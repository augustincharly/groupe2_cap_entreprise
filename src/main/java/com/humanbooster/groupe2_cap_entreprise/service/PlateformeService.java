package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;
import com.humanbooster.groupe2_cap_entreprise.repository.PlateformeRepository;

@Component
public class PlateformeService implements IPlateformeService {

	@Autowired
	private PlateformeRepository plateformeRepository;
	
	@Override
	public void save(Plateforme plateforme) {
		plateformeRepository.save(plateforme);
	}

}
