package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;
import com.humanbooster.groupe2_cap_entreprise.repository.ModeleEconomiqueRepository;

@Component
public class ModeleEconomiqueService implements IModeleEconomiqueService{

	@Autowired
	private ModeleEconomiqueRepository modeleEconomiqueRepository;
	
	@Override
	public void save(ModeleEconomique modeleEconomique) {
		modeleEconomiqueRepository.save(modeleEconomique);
	}

	@Override
	public List<ModeleEconomique> getAllModeleEconomiques() {
		return modeleEconomiqueRepository.findAll();
	}

	@Override
	public ModeleEconomique findModeleEconomiqueById(Long modeleEconomique_id) {
		return modeleEconomiqueRepository.findById(modeleEconomique_id).get();
	}

}
