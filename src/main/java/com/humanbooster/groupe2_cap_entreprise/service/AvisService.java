package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.repository.AvisRepository;

public class AvisService {

	@Autowired
	private AvisRepository avisRepository;

	public List<Avis> getAvis() {
		return avisRepository.findAll();
	}
	

}
