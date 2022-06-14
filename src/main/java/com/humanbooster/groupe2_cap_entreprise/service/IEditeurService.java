package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Editeur;

@Component
public interface IEditeurService {

	public void save(Editeur editeur1);

	public List<Editeur> getAllEditeurs();

	public Editeur findEditeurById(Long editeur_id);

}
