package com.humanbooster.groupe2_cap_entreprise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humanbooster.groupe2_cap_entreprise.entity.Jeu;

@Repository
public interface JeuRepository extends JpaRepository<Jeu, Long>{

	
	Page<Jeu> findAll(Pageable pageable);
}
