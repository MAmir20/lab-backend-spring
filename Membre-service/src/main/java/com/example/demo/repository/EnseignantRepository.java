package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.EnseignantChercheur;

public interface EnseignantRepository extends JpaRepository<EnseignantChercheur, Long>{
	List<EnseignantChercheur>findByGrade(String grade);
	List<EnseignantChercheur>findByEstablishment(String establishment);
	
	@Query("SELECT m.establishment, COUNT(m) FROM Membre m WHERE m.establishment IS NOT NULL GROUP BY m.establishment")
	List<Object[]> countProfessorsByEstablishments();
}
