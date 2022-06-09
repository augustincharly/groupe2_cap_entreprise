package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Editeur;
import com.humanbooster.groupe2_cap_entreprise.repository.EditeurRepository;

@Component
public class EditeurService implements IEditeurService{

	@Autowired
	private EditeurRepository editeurRepository;
	
	@Override
	public void save(Editeur editeur) {
		editeurRepository.save(editeur);		
	}

}
