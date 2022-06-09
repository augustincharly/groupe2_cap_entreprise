package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;

@Component
public interface IModeleEconomiqueService {

	void save(ModeleEconomique modeleEconomique);

}
