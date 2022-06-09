package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.ArrayList;
import java.util.List;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;

public interface IAvisService {

	public List<Avis> getAvis();

	public List<AvisDTO> getAvisDTOs();
	
}