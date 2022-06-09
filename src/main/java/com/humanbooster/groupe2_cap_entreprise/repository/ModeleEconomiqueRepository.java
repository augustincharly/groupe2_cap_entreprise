package com.humanbooster.groupe2_cap_entreprise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humanbooster.groupe2_cap_entreprise.entity.ModeleEconomique;

@Repository
public interface ModeleEconomiqueRepository extends JpaRepository<ModeleEconomique, Long>{

}
