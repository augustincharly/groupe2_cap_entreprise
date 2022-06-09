package com.humanbooster.groupe2_cap_entreprise.service;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Genre;

@Component
public interface IGenreService {

	void save(Genre genre1);

}
