package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Classification;
import com.humanbooster.groupe2_cap_entreprise.repository.ClassificationRepository;

@Component
public class ClassificationService implements IClassificationService{

	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	@Override
	public void save(Classification classification) {
		classificationRepository.save(classification);
	}

}
