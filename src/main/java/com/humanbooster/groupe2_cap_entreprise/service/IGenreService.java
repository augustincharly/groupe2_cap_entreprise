package com.humanbooster.groupe2_cap_entreprise.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.humanbooster.groupe2_cap_entreprise.entity.Genre;

@Component
public interface IGenreService {

	public void save(Genre genre1);

	public List<Genre> getAllGenres();

	public Genre findGenreById(Long editeur_id);

}
