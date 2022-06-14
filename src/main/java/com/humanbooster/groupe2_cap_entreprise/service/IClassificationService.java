package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Classification;

@Component
public interface IClassificationService {

	public void save(Classification classification1);

	public List<Classification> getAllClassifications();

	public Classification findClassificationById(Long classification_id);

}
