package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Plateforme;

@Component
public interface IPlateformeService {

	public void save(Plateforme plateforme);

	public List<Plateforme> getAllPlateformes();

	public Plateforme findPlateformeById(Long plateformeId);




}
