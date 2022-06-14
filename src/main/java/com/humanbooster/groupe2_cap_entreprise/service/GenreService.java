package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Genre;
import com.humanbooster.groupe2_cap_entreprise.repository.GenreRepository;

@Component
public class GenreService implements IGenreService{

	
	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public void save(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}

	@Override
	public Genre findGenreById(Long editeur_id) {
		return genreRepository.findById(editeur_id).get();
	}

}
