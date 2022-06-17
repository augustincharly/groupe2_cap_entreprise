package com.humanbooster.groupe2_cap_entreprise.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.humanbooster.groupe2_cap_entreprise.entity.Avis;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {

	@Query("SELECT a FROM Avis a WHERE a.joueur.pseudo = ?1 or a.moderateur is not null")
	Page<Avis> findAll(String pseudo, Pageable pageable);
	
	Page<Avis> findAll(Pageable pageable);
	
	@Query("SELECT a FROM Avis a WHERE a.joueur.pseudo = ?1 or a.moderateur is not null")
	List<Avis> findAll(String pseudo);
}
