package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;

@Component
public interface IModeleEconomiqueService {

	public void save(ModeleEconomique modeleEconomique);

	public List<ModeleEconomique> getAllModeleEconomiques();

	public ModeleEconomique findModeleEconomiqueById(Long modeleEconomique_id);

}
