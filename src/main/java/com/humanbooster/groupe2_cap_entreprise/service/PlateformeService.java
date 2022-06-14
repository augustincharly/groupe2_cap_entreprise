package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

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

	@Override
	public List<Plateforme> getAllPlateformes() {
		return plateformeRepository.findAll();
	}

	@Override
	public Plateforme findPlateformeById(Long plateformeId) {
		return plateformeRepository.findById(plateformeId).get();
	}



}
