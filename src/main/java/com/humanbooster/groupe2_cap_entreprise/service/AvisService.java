package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanbooster.groupe2_cap_entreprise.dto.AvisDTO;
import com.humanbooster.groupe2_cap_entreprise.entity.Avis;
import com.humanbooster.groupe2_cap_entreprise.repository.AvisRepository;
import com.humanbooster.groupe2_cap_entreprise.transformer.TransformerFactory;

@Service
public class AvisService implements IAvisService {

	@Autowired
	private AvisRepository avisRepository;

	public List<Avis> getAvis() {
		return avisRepository.findAll();
	}

	public List<AvisDTO> getAvisDTOs() {
		List<AvisDTO> avisDTOs = new ArrayList<AvisDTO>();

		avisRepository.findAll().forEach(avis -> {
			AvisDTO avisDTO = TransformerFactory.getAvisTransformer().transform(avis);
			avisDTOs.add(avisDTO);
		});

		return avisDTOs;
	}

	@Override
	public AvisDTO getAvisDTO(Long id) {
		Avis avisEntity = avisRepository.findById(id).get();
		return TransformerFactory.getAvisTransformer().transform(avisEntity, true);

	}

}
