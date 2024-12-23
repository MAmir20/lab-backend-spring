package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EnseignantChercheur;

public interface EnseignantRepository extends JpaRepository<EnseignantChercheur, Long>{
	List<EnseignantChercheur>findByGrade(String grade);
	List<EnseignantChercheur>findByEtablissement(String etablissement);
}
